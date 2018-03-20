package com.liuvlun.timer;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * Created by Administrator on 2018/3/20 0020.
 */
public class TimeDecoder  extends ByteToMessageDecoder{
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {

        if(in.readableBytes()<4){
            return;
        }

        out.add(new UnixTime(in.readUnsignedInt() ));
    }
}
