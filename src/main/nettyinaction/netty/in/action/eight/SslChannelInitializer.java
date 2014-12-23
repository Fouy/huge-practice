package netty.in.action.eight;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.handler.ssl.SslHandler;

public class SslChannelInitializer extends ChannelInitializer<Channel> {

	private final SSLContext context;
	private final boolean client;
	private final boolean startTls;

	public SslChannelInitializer(SSLContext context, boolean client,
			boolean startTls) {
		this.context = context;
		this.client = client;
		this.startTls = startTls;
	}

	@Override
	protected void initChannel(Channel ch) throws Exception {
		SSLEngine engine = context.createSSLEngine();
		engine.setUseClientMode(client);
		ch.pipeline().addFirst("ssl", new SslHandler(engine, startTls));
	}
}
