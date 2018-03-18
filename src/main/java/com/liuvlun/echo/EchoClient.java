package com.liuvlun.echo;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * Created by Administrator on 2018/3/17 0017.
 */
public class EchoClient {
    static final int PORT = 8090;
    static final String HOST = "127.0.0.1";


    public static void main(String[] args){

        EventLoopGroup group = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();


        bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY,true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new EchoClientHandler());
                        }
                    });


        try {
            ChannelFuture f =  bootstrap.connect(HOST,PORT).sync();

            f.channel().closeFuture().sync();

        } catch (InterruptedException e) {
            group.shutdownGracefully();
        }


    }

}
