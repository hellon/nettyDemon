package com.liuvlun.Timer1;

import com.liuvlun.utils.PrintUtil;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * Created by Administrator on 2018/4/16 0016.
 */
public class TimerClient {

    public static void main(String[] args){


        EventLoopGroup el = new NioEventLoopGroup();

        Bootstrap b = new Bootstrap();


        b.group(el)
          .channel(NioSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY,true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new LineBasedFrameDecoder(1024))
                                .addLast(new StringDecoder())
                                .addLast(new StringEncoder())
                                .addLast(new TimerClientHandler());
                    }
                });

        try {
            ChannelFuture f = b.connect("localhost",8080).sync();
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally{
            el.shutdownGracefully();
        }
    }



}


class TimerClientHandler extends SimpleChannelInboundHandler<String>{

    private int counter;

    private byte[] msg = ("QUERY TIME ORDER" + System.getProperty("line.separator")).getBytes();


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        ByteBuf mess = null;

        for(int i = 0; i < 100; i++){
            mess = Unpooled.buffer(msg.length);
            mess.writeBytes(msg);
            //ctx.writeAndFlush(mess);
            ctx.writeAndFlush("QUERY TIME ORDER" + System.getProperty("line.separator"));
        }
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {

        PrintUtil.sysPrintln("Now is :" + msg + "; the counter is :" + ++counter);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}