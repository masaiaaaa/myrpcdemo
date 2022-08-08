package com.sai.version7.codec;

/**
 * @Description: TODO
 * @author: sai
 * @date: 2022年08月08日 20:26
 */

import com.sai.version7.common.RPCRequest;
import com.sai.version7.common.RPCResponse;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import lombok.AllArgsConstructor;

/**
 * 依次按照自定义的消息格式写入，传入的数据为request或者response
 * 需要持有一个serialize器，负责将传入的对象序列化成字节数组
 */
@AllArgsConstructor
public class MyEncode extends MessageToByteEncoder {
    private Serializer serializer;
    @Override
    protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out) throws Exception {
        if(msg instanceof RPCRequest){
            out.writeShort(MessageType.REQUEST.getCode());
        }else if(msg instanceof RPCResponse){
            out.writeShort(MessageType.RESPONSE.getCode());
        }
        out.writeShort(serializer.getType());
        byte[] serialize = serializer.serialize(msg);
        out.writeInt(serialize.length);
        out.writeBytes(serialize);
    }
}
