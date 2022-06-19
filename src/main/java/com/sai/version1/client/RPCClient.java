package com.sai.version1.client;

import com.sai.version1.common.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Random;

/**
 * @Description: TODO
 * @author: sai
 * @date: 2022年06月19日 17:59
 */
public class RPCClient {
    public static void main(String[] args) {
        try {
            //创建一个socket连接
            Socket socket = new Socket("127.0.0.1", 8899);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            //传给服务器id
            objectOutputStream.writeInt(new Random().nextInt());
            objectOutputStream.flush();
            //服务器返回查询数据
            User user = (User) objectInputStream.readObject();
            System.out.println("服务器返回的user："+user);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("客户端启动失败");
        }

    }
}
