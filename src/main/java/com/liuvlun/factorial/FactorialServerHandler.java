package com.liuvlun.factorial;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.math.BigInteger;

/**
 * Created by Administrator on 2018/3/27 0027.
 */
public class FactorialServerHandler extends SimpleChannelInboundHandler<BigInteger>{
    protected void channelRead0(ChannelHandlerContext ctx, BigInteger msg) throws Exception {

    }




}
