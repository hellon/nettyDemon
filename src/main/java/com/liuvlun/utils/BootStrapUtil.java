package com.liuvlun.utils;

import com.liuvlun.echo.EchoClientHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * Created by Administrator on 2018/3/18 0018.
 */
public class BootStrapUtil {

    public static void serverStart(int port,final ChannelHandler... handler){

        EventLoopGroup boss = new NioEventLoopGroup(1);
        EventLoopGroup worker = new NioEventLoopGroup();

        ServerBootstrap b = new ServerBootstrap();


        b.group(boss,worker)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG,100)
                .handler(new LoggingHandler(LogLevel.INFO))
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(handler);
                    }
                });

        try {
            ChannelFuture future = b.bind(port).sync();

            future.channel().closeFuture().sync();


        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally{
            boss.shutdownGracefully();
            worker.shutdownGracefully();
        }
    }


    public static void clientStart(String host,int port,final ChannelHandler... handler){

        EventLoopGroup group = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();


        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY,true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(handler);
                    }
                });


        try {
            ChannelFuture f =  bootstrap.connect(host,port).sync();

            f.channel().closeFuture().sync();

        } catch (InterruptedException e) {
          e.printStackTrace();
        }finally {
            group.shutdownGracefully();
        }



    }

}
