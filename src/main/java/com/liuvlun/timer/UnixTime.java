package com.liuvlun.timer;

import com.liuvlun.utils.PrintUtil;

import java.sql.Date;

/**
 * Created by Administrator on 2018/3/20 0020.
 */
public class UnixTime {
    private final long value;

    public UnixTime(){
        this(System.currentTimeMillis()/1000L + 2208988800L);

    }


    public UnixTime(long value){
        this.value = value;
    }



    public long value(){
        return value;
    }

    @Override
    public String toString() {
        return new Date((value - 2208988800L) * 1000L).toLocaleString();
    }


    public static void main(String[] args){
        PrintUtil.sysPrintln(new UnixTime().toString());
    }
}
