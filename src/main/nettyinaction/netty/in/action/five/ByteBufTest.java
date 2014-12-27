package netty.in.action.five;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Random;

import org.junit.Test;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.CompositeByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * 
 * @author xuefeihu
 *
 */
public class ByteBufTest {

	/**
	 * 复合数组缓冲区测试
	 */
	@Test
	public void testCompositeByteBuf() {
		CompositeByteBuf compBuf = Unpooled.compositeBuffer();
		ByteBuf heapBuf = Unpooled.buffer(8);
		ByteBuf directBuf = Unpooled.directBuffer(16);
		// 添加ByteBuf到CompositeByteBuf
		compBuf.addComponents(heapBuf, directBuf);
		// 删除第一个ByteBuf
		compBuf.removeComponent(0);
		Iterator<ByteBuf> iter = compBuf.iterator();
		while (iter.hasNext()) {
			System.out.println(iter.next().toString());
		}
		// 使用数组访问数据
		if (!compBuf.hasArray()) {
			int len = compBuf.readableBytes();
			byte[] arr = new byte[len];
			compBuf.getBytes(0, arr);
		}
	}

	@Test
	public void testByteBuf1() {
		// create a ByteBuf of capacity is 16
		ByteBuf buf = Unpooled.buffer(16);
		// write data to buf
		for (int i = 0; i < 16; i++) {
			buf.writeByte(i + 1);
		}
		// read data from buf
		for (int i = 0; i < buf.capacity(); i++) {
			System.out.println(buf.getByte(i));
		}
	}
	
	@Test
	public void testByteBuf2() {
		ByteBuf buf = Unpooled.buffer(16);
		while (buf.isReadable()) {
			System.out.println(buf.readByte());
		}
	}
	
	@Test
	public void testByteBuf3() {
		Random random = new Random();
		ByteBuf buf = Unpooled.buffer(16);
		while (buf.writableBytes() >= 4) {
			System.out.println(buf.writableBytes());
			buf.writeInt(random.nextInt());
		}
	}
	
	@Test
	public void testByteBuf4() {
		// get a Charset of UTF-8
		Charset utf8 = Charset.forName("UTF-8");
		// get a ByteBuf
		ByteBuf buf = Unpooled.copiedBuffer("Netty in Action rocks!", utf8);
		// slice
		ByteBuf sliced = buf.slice(0, 14);
		// copy
		ByteBuf copy = buf.copy(0, 14);
		// print "Netty in Action rocks!"
		System.out.println(buf.toString(utf8));
		// print "Netty in Act"
		System.out.println(sliced.toString(utf8));
		// print "Netty in Act"
		System.out.println(copy.toString(utf8));
	}
	
	@Test
	public void testByteBuf5() {
		int port = 65535;
		final ByteBuf buf = Unpooled.copiedBuffer("Hi, I am huge.", Charset.forName("UTF-8"));
		EventLoopGroup group = new NioEventLoopGroup();
		ServerBootstrap b = new ServerBootstrap();
		b.group(group).channel(NioServerSocketChannel.class)
				.localAddress(new InetSocketAddress(port))
				.childHandler(new ChannelInitializer<SocketChannel>() {
					@Override
					protected void initChannel(SocketChannel ch) throws Exception {
						// get ByteBufAllocator instance by Channel.alloc()
						ByteBufAllocator alloc0 = ch.alloc();
						ch.pipeline().addLast(
								new ChannelInboundHandlerAdapter() {
									@Override
									public void channelActive(ChannelHandlerContext ctx) throws Exception {
										// get ByteBufAllocator instance by ChannelHandlerContext.alloc()
										ByteBufAllocator alloc1 = ctx.alloc();
										ctx.writeAndFlush(buf.duplicate())
												.addListener(ChannelFutureListener.CLOSE);
									}
								});
					}
				});
	}
	
	@Test
	public void testByteBuf6() {
		// 创建复合缓冲区
		CompositeByteBuf compBuf = Unpooled.compositeBuffer();
		// 创建堆缓冲区
		ByteBuf heapBuf = Unpooled.buffer(8);
		// 创建直接缓冲区
		ByteBuf directBuf = Unpooled.directBuffer(16);
	}
	
	@Test
	public void testByteBuf7(){
		Charset utf8 = Charset.forName("UTF-8");
		ByteBuf buf = Unpooled.copiedBuffer("fuck you man.", utf8);
		ByteBuf sliced = buf.slice(0, 8);
		ByteBuf copy = buf.copy(0, 8);
		System.out.println(buf.toString(utf8));
		System.out.println(sliced.toString(utf8));
		System.out.println(copy.toString(utf8));
	}
	
	
	
	
	
	

}
