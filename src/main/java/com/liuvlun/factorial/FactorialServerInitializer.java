package com.liuvlun.factorial;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;

/**
 * Created by Administrator on 2018/3/27 0027.
 */
public class FactorialServerInitializer  extends ChannelInitializer{
    protected void initChannel(Channel ch) throws Exception {
        ch.pipeline().addLast();
        ch.pipeline().addLast();
        ch.pipeline().addLast();
        ch.pipeline().addLast();
        ch.pipeline().addLast(new FactorialServerHandler());
    }
}
