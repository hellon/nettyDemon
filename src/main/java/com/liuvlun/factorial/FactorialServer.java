package com.liuvlun.factorial;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

import java.nio.channels.ServerSocketChannel;

/**
 * Created by Administrator on 2018/3/27 0027.
 */
public class FactorialServer {


    public static void main(String[] args){


        EventLoopGroup boss = new NioEventLoopGroup(1);

        EventLoopGroup worker = new NioEventLoopGroup();

        ServerBootstrap server = new ServerBootstrap();


        server.group(boss,worker)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG,100)
                .handler(new LoggingHandler(LogLevel.INFO))
                .childHandler(new FactorialServerInitializer());


    }




}
