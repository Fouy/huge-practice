package netty.in.action.two.huge;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoop;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class PrintServer {

	private static final int port = 8888;
	
	public static void main(String[] args) throws InterruptedException {
		new PrintServer().startServer(port);
	}

	private void startServer(int port) throws InterruptedException {
		EventLoopGroup group = new NioEventLoopGroup();
		
		try {
			ServerBootstrap bootstrap = new ServerBootstrap();
			bootstrap.group(group).channel(NioServerSocketChannel.class)
				.localAddress(port).childHandler(new ChannelInitializer<Channel>() {
					@Override
					protected void initChannel(Channel ch) throws Exception {
						ch.pipeline().addLast(new PrintServerHandler());
					}
				});
			
			ChannelFuture future = bootstrap.bind().sync();
			System.out.println("server started on port : " + port);
			future.channel().closeFuture().sync();
		} finally {
			group.shutdownGracefully();
		}
	}
	
	

}
