package com.liuvlun.timer;


import com.liuvlun.utils.PrintUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Date;

/**
 * Created by Administrator on 2018/3/19 0019.
 */
public class TimeClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        ByteBuf time = (ByteBuf) msg;
        long longTime = (time.readUnsignedInt() - 2208988800L) * 1000L;

        PrintUtil.sysPrintln(new Date(longTime).toLocaleString());

        time.release();

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
