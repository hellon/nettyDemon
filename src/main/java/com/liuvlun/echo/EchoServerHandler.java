package com.liuvlun.echo;

import com.liuvlun.utils.PrintUtil;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * Created by Administrator on 2018/3/17 0017.
 */
@Sharable
public class EchoServerHandler  extends ChannelInboundHandlerAdapter{

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        String id = ctx.channel().id().toString();

        ByteBuf in = (ByteBuf) msg;
        PrintUtil.sysPrintln("server recieved :[" + id + "]" + in.toString(CharsetUtil.UTF_8));

        ctx.write(Unpooled.copiedBuffer("server has recived message from client,i am ok!!!!",CharsetUtil.UTF_8));
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
