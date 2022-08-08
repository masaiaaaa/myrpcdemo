package com.sai.version6.codec;

import java.io.*;

/**
 * @Description: TODO
 * @author: sai
 * @date: 2022年08月08日 20:27
 */
public class ObjectSerializer implements Serializer{
    @Override
    public byte[] serialize(Object obj) {
        byte[] bytes = null;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(obj);

            objectOutputStream.flush();
            bytes = byteArrayOutputStream.toByteArray();

            objectOutputStream.close();
            byteArrayOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bytes;
    }

    @Override
    public Object deserialize(byte[] bytes, int messageType) {
        Object obj = null;

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
            obj = objectInputStream.readObject();

            objectInputStream.close();
            byteArrayInputStream.close();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return obj;
    }

    @Override
    public int getType() {
        return 0;
    }
}
