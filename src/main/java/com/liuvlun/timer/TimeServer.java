package com.liuvlun.timer;

import com.liuvlun.utils.BootStrapUtil;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * Created by Administrator on 2018/3/18 0018.
 */
public class TimeServer {


    static final int PORT  = 8080;


    public static void main(String[] args){

        BootStrapUtil.serverStart(PORT,new TimeServerHandler());


    }



}
