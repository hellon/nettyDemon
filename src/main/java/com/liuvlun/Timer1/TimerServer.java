package com.liuvlun.Timer1;

import com.liuvlun.utils.PrintUtil;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;


/**
 * Created by Administrator on 2018/4/16 0016.
 */
public class TimerServer {

    public static void main(String[] args){

        EventLoopGroup boss = new NioEventLoopGroup();

        EventLoopGroup worker = new NioEventLoopGroup();


        ServerBootstrap sb = new ServerBootstrap();


        sb.group(boss,worker)
            .option(ChannelOption.SO_BACKLOG,100)
            .channel(NioServerSocketChannel.class)
            .handler(new LoggingHandler(LogLevel.INFO))
            .childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline()
                            .addLast(new LineBasedFrameDecoder(1024))
                            .addLast(new StringDecoder())
                            .addLast(new StringEncoder())
                            .addLast(new TimerHandler());
                }
            });

        try {
           ChannelFuture futrue =  sb.bind(8080).sync();
           futrue.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            boss.shutdownGracefully();
            worker.shutdownGracefully();
        }
    }

}



class TimerHandler extends SimpleChannelInboundHandler<String>{
    private int counter;

    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        PrintUtil.sysPrintln("the server recieve order :" + msg + ";the counter is " + (++counter));

        String currentTime = ("QUERY TIME ORDER".equalsIgnoreCase(msg) ? new java.util.Date(System.currentTimeMillis()).toString() : "BAD ORDER") ;

        currentTime = currentTime + System.getProperty("line.separator");

        PrintUtil.sysPrintln(currentTime);


        ByteBuf mess = Unpooled.copiedBuffer(currentTime.getBytes());

        ctx.writeAndFlush(currentTime);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}