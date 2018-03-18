package com.liuvlun.echo;

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
 * Created by Administrator on 2018/3/17 0017.
 */
public class EchoServer {
    static final int PORRT = 8090;


    public static void main(String[] args){

        //channel接受线程组
        EventLoopGroup boss = new NioEventLoopGroup(1);
        //channel io事件处理线程组
        EventLoopGroup worker = new NioEventLoopGroup();


        ServerBootstrap s =  new ServerBootstrap();


        ServerBootstrap serverBootstrap = s.group(boss, worker)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 100)
                .handler(new LoggingHandler(LogLevel.INFO))
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new EchoServerHandler());
                    }
                });

        try {
            ChannelFuture f = serverBootstrap.bind(PORRT).sync();
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();

        }finally {
            boss.shutdownGracefully();
            worker.shutdownGracefully();
        }
    }





}
