package netty.in.action.eight;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpContentDecompressor;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * HTTP协议防止粘包+压缩
 * @author xuefeihu
 *
 */
public class HttpCompressInitializer extends ChannelInitializer<Channel> {
	
	private final boolean client;
	
	public HttpCompressInitializer(boolean client){
		this.client = client;
	}

	@Override
	protected void initChannel(Channel ch) throws Exception {
		ChannelPipeline pipeline = ch.pipeline();
		if(client){
			pipeline.addLast("codec", new HttpClientCodec());
			pipeline.addLast("decompressor", new HttpContentDecompressor());
		} else {
			pipeline.addLast("codec", new HttpServerCodec());
			pipeline.addLast("decompressor", new HttpContentDecompressor());
		}
		pipeline.addLast("aggegator", new HttpObjectAggregator(512 * 1024));
	}

}
