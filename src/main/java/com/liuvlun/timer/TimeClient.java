package com.liuvlun.timer;

import com.liuvlun.utils.BootStrapUtil;
import io.netty.channel.ChannelHandler;

/**
 * Created by Administrator on 2018/3/19 0019.
 */
public class TimeClient {

    final  static int PORT = 8080;
    final static String HOST = "localhost";

    public static void main(String[] args){

        ChannelHandler[] handlers = {new TimeDecoder(),new TimeEncoder(),new TimeClientHandler()};
        BootStrapUtil.clientStart(HOST,PORT,handlers);


    }




}
