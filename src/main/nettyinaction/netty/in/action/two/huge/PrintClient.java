package netty.in.action.two.huge;

import java.net.InetSocketAddress;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

public class PrintClient {
	
	private final String ip ;
	
	private final int port ;
	
	public PrintClient(String ip, int port) {
		this.ip = ip;
		this.port = port;
	}
	
	public static void main(String[] args) throws InterruptedException {
		new PrintClient("127.0.0.1", 8888).connectServer();
	}

	private void connectServer() throws InterruptedException {
		EventLoopGroup group = new NioEventLoopGroup();
		
		try {
			Bootstrap bootstrap = new Bootstrap();
			bootstrap.group(group).channel(NioSocketChannel.class)
				.remoteAddress(new InetSocketAddress(ip, port))
				.handler(new ChannelInitializer<Channel>() {
					@Override
					protected void initChannel(Channel ch) throws Exception {
						ch.pipeline().addLast(new PrintClientHandler());
					}
				});
			
			ChannelFuture future = bootstrap.connect().sync();
			future.channel().closeFuture().sync();
			
		} finally {
			group.shutdownGracefully().sync();
		}
		
	}

}
