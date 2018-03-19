package com.liuvlun.timer;

import com.liuvlun.utils.BootStrapUtil;

/**
 * Created by Administrator on 2018/3/19 0019.
 */
public class TimeClient {

    final  static int PORT = 8080;
    final static String HOST = "localhost";

    public static void main(String[] args){

        BootStrapUtil.clientStart(HOST,PORT,new TimeClientHandler());


    }




}
