package com.liuvlun.echo;

import com.liuvlun.utils.PrintUtil;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * Created by Administrator on 2018/3/17 0017.
 */
public class EchoClientHandler extends ChannelInboundHandlerAdapter{

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        PrintUtil.sysPrintln("客户端与服务端建立连接.............");

        ctx.writeAndFlush(Unpooled.copiedBuffer("netty 客户端连接..........", CharsetUtil.UTF_8));
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        String id = ctx.channel().id().toString();

        ByteBuf in = (ByteBuf) msg;

        PrintUtil.sysPrintln("client recieved message : " + in.toString(CharsetUtil.UTF_8)) ;

        ctx.write(Unpooled.copiedBuffer("client id[" + id + "] has recived message from server,i am ok!!!!",CharsetUtil.UTF_8));
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }



}
