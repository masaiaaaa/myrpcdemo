package com.sai.version6.codec;



public interface Serializer {
    //把对象序列化成字节数组
    byte[] serialize(Object obj);
    //反序列化
    Object deserialize(byte[] bytes, int messageType);
    //返回使用的序列化器
    int getType();
    //根据序号取出序列化器
    static Serializer getSerializerByCode(int code){
        switch (code){
            case 0:
                return new ObjectSerializer();
            case 1:
                return new JsonSerializer();
            default:
                return null;
        }
    }
}
