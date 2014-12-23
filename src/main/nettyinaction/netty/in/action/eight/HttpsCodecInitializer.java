package netty.in.action.eight;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.ssl.SslHandler;

/**
 * 使用SSL对HTTP消息加密
 * @author xuefeihu
 *
 */
public class HttpsCodecInitializer extends ChannelInitializer<Channel> {

	private final SSLContext context;
	private final boolean client;

	public HttpsCodecInitializer(SSLContext context, boolean client) {
		this.context = context;
		this.client = client;
	}

	@Override
	protected void initChannel(Channel ch) throws Exception {
		SSLEngine engine = context.createSSLEngine();
		engine.setUseClientMode(client);
		ChannelPipeline pipeline = ch.pipeline();
		pipeline.addFirst("ssl", new SslHandler(engine));
		if (client) {
			pipeline.addLast("codec", new HttpClientCodec());
		} else {
			pipeline.addLast("codec", new HttpServerCodec());
		}
	}

}
