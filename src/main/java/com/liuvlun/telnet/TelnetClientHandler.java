package com.liuvlun.telnet;

import com.liuvlun.utils.PrintUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Created by Administrator on 2018/3/22 0022.
 */
public class TelnetClientHandler  extends SimpleChannelInboundHandler<String>{
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        PrintUtil.sysPrintln(msg);
    }
}
