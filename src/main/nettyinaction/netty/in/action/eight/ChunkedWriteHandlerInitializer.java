package netty.in.action.eight;

import java.io.File;
import java.io.FileInputStream;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.handler.stream.ChunkedStream;
import io.netty.handler.stream.ChunkedWriteHandler;

public class ChunkedWriteHandlerInitializer extends ChannelInitializer<Channel> {
    private final File file;  
  
    public ChunkedWriteHandlerInitializer(File file) {  
        this.file = file;  
    }  
  
    @Override  
    protected void initChannel(Channel ch) throws Exception {  
        ch.pipeline().addLast(new ChunkedWriteHandler())  
            .addLast(new WriteStreamHandler());  
    }  
  
    public final class WriteStreamHandler extends ChannelHandlerAdapter { 
        @Override  
        public void channelActive(ChannelHandlerContext ctx) throws Exception {  
            super.channelActive(ctx);  
            ctx.writeAndFlush(new ChunkedStream(new FileInputStream(file)));  
        }  
    }  
} 
