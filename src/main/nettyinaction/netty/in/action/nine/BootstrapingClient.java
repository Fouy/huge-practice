package netty.in.action.nine;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * 引导配置客户端
 * 
 * @author c.k
 * 
 */
public class BootstrapingClient {

	public static void main(String[] args) throws Exception {
		EventLoopGroup group = new NioEventLoopGroup();
		Bootstrap b = new Bootstrap();
		b.group(group).channel(NioSocketChannel.class)
				.handler(new SimpleChannelInboundHandler<ByteBuf>() {
					@Override
					protected void messageReceived(ChannelHandlerContext ctx,
							ByteBuf msg) throws Exception {
						System.out.println("Received data");
						msg.clear();
					}
				});
		ChannelFuture f = b.connect("127.0.0.1", 2048);
		f.addListener(new ChannelFutureListener() {
			@Override
			public void operationComplete(ChannelFuture future)
					throws Exception {
				if (future.isSuccess()) {
					System.out.println("connection finished");
				} else {
					System.out.println("connection failed");
					future.cause().printStackTrace();
				}
			}
		});
	}
}
