package com.liuvlun.echo;

import com.liuvlun.utils.BootStrapUtil;


/**
 * Created by Administrator on 2018/3/17 0017.
 */
public class EchoClient {
    static final int PORT = 8090;
    static final String HOST = "127.0.0.1";


    public static void main(String[] args){

        BootStrapUtil.clientStart(HOST,PORT,new EchoClientHandler());
    }

}
