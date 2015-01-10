package netty.in.action.nine;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * 引导服务器配置
 * 
 * @author c.k
 * 
 */
public class BootstrapingServer {

	public static void main(String[] args) throws Exception {
		EventLoopGroup bossGroup = new NioEventLoopGroup(1);
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		ServerBootstrap b = new ServerBootstrap();
		b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
				.childHandler(new SimpleChannelInboundHandler<ByteBuf>() {
					@Override
					protected void messageReceived(ChannelHandlerContext ctx,
							ByteBuf msg) throws Exception {
						System.out.println("Received data");
						msg.clear();
					}
				});
		ChannelFuture f = b.bind(2048);
		f.addListener(new ChannelFutureListener() {
			@Override
			public void operationComplete(ChannelFuture future)
					throws Exception {
				if (future.isSuccess()) {
					System.out.println("Server bound");
				} else {
					System.err.println("bound fail");
					future.cause().printStackTrace();
				}
			}
		});
	}
}
