package com.liuvlun.echo;

import com.liuvlun.utils.BootStrapUtil;


/**
 * Created by Administrator on 2018/3/17 0017.
 */
public class EchoServer {
    static final int PORRT = 8090;


    public static void main(String[] args){

        BootStrapUtil.serverStart(PORRT, new EchoServerHandler());
    }





}
