package com.liuvlun.timer;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * Created by Administrator on 2018/3/19 0019.
 */
public class TimeServerHandler  extends ChannelInboundHandlerAdapter{

    @Override
    public void channelActive(final ChannelHandlerContext ctx) throws Exception {
        ByteBuf time = ctx.channel().alloc().buffer(4);
        int  intTime = (int) (System.currentTimeMillis()/1000L + 2208988800L);
        time.writeInt(intTime);

        final ChannelFuture f = ctx.writeAndFlush(time);

        f.addListener(new ChannelFutureListener(){
            public void operationComplete(ChannelFuture future) throws Exception {
                assert f == future;
                ctx.close();
            }
        });
    }



    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
