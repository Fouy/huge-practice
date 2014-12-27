package netty.in.action.eight;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.LineBasedFrameDecoder;

/**
 * 处理换行分隔符消息
 * @author xuefeihu
 *
 */
public class LineBasedHandlerInitializer extends ChannelInitializer<Channel> {  
  
    @Override  
    protected void initChannel(Channel ch) throws Exception {  
        ch.pipeline().addLast(new LineBasedFrameDecoder(65 * 1204), new FrameHandler());  
    }  
  
    public static final class FrameHandler extends SimpleChannelInboundHandler<ByteBuf> {  

		@Override
		protected void messageReceived(ChannelHandlerContext ctx, ByteBuf msg)
				throws Exception {
			// TODO Auto-generated method stub
			
		}  
    }  
} 
