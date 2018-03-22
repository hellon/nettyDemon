package com.liuvlun.telnet;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * Created by Administrator on 2018/3/22 0022.
 */
public class TlenetServerHandlerInniter extends ChannelInitializer{

    //String 解码器
    private final StringDecoder DECONDER = new StringDecoder();
    //String 编码器
    private final StringEncoder ENCODER = new StringEncoder();


    protected void initChannel(Channel ch) throws Exception {

        ChannelPipeline pipeline = ch.pipeline();

        pipeline.addLast(new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()));

        pipeline.addLast(DECONDER);
        pipeline.addLast(ENCODER);
        pipeline.addLast(new TelnetServerHandler());

    }
}
