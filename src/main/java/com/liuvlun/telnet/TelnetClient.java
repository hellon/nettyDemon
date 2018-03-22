package com.liuvlun.telnet;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by Administrator on 2018/3/22 0022.
 */
public class TelnetClient {

    public static void main(String[] args){

        EventLoopGroup group = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();


        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY,true)
                .handler(new TelnetClinetHandlerInniter());


        try {

            Channel ch = bootstrap.connect("localhost",8088).sync().channel();

            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            ChannelFuture future;

            for(;;){
                try {
                    String msg = in.readLine();

                    future = ch.writeAndFlush(msg + "\r\n");

                    if("bye".equals(msg)){
                        ch.closeFuture().sync();
                        break;
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if(future != null){
                future.sync();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            group.shutdownGracefully();
        }



    }


}
