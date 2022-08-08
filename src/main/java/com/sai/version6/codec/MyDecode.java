package com.sai.version6.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @Description: TODO
 * @author: sai
 * @date: 2022年08月08日 20:38
 */
public class MyDecode extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        short messageType = in.readShort();

        if(messageType != MessageType.REQUEST.getCode()
        && messageType != MessageType.RESPONSE.getCode()){
            System.out.println("目前尚不支持这种数据");
            return;
        }
        short serializerType = in.readShort();
        Serializer serializer = Serializer.getSerializerByCode(serializerType);

        if(serializer == null){
            throw new RuntimeException("不存在对应的序列化器");
        }

        int length = in.readInt();

        byte[] bytes = new byte[length];

        in.readBytes(bytes);

        Object deserialize = serializer.deserialize(bytes, messageType);
        out.add(deserialize);

    }
}
