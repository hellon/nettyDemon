package com.liuvlun.telnet;


import com.liuvlun.utils.PrintUtil;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.time.LocalDateTime;


/**
 * Created by Administrator on 2018/3/22 0022.
 */
public class TelnetServerHandler extends SimpleChannelInboundHandler<String>{


    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {

        String out = "服务端接受消息为:" + msg ;


        PrintUtil.sysPrintln(out + "\r\n");

        ChannelFuture f = ctx.write(out);

        if("bye".equals(msg)){
            f.addListener(ChannelFutureListener.CLOSE);
        }





    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        PrintUtil.sysPrintln("服务端建立连接，发送消息!");

        String msg = "服务端建立连接，首次发送消息到客户端\r\n 服务器时间为:" + LocalDateTime.now() + "\r\n";

        ctx.writeAndFlush(msg);
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
