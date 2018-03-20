package com.liuvlun.timer;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

/**
 * Created by Administrator on 2018/3/20 0020.
 */
public class TimeEncoder extends ChannelOutboundHandlerAdapter{
    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {

        UnixTime time = (UnixTime) msg;

        ByteBuf buf = ctx.alloc().buffer(4);

        buf.writeInt((int) time.value());
        ctx.write(buf,promise);

    }
}
