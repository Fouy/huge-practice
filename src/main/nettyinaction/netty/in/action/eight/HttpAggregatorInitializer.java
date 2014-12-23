package netty.in.action.eight;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * 添加聚合http消息的Handler
 * @author xuefeihu
 *
 */
public class HttpAggregatorInitializer extends ChannelInitializer<Channel> {

	private final boolean client;

	public HttpAggregatorInitializer(boolean client) {
		this.client = client;
	}

	@Override
	protected void initChannel(Channel ch) throws Exception {
		ChannelPipeline pipeline = ch.pipeline();
		if (client) {
			pipeline.addLast("codec", new HttpClientCodec());
		} else {
			pipeline.addLast("codec", new HttpServerCodec());
		}
		pipeline.addLast("aggegator", new HttpObjectAggregator(512 * 1024));
	}

}
