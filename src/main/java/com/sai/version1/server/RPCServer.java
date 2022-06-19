package com.sai.version1.server;

import com.sai.version1.common.User;
import com.sai.version1.service.UserService;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Description: TODO
 * @author: sai
 * @date: 2022年06月19日 18:06
 */
public class RPCServer {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();

        try {
            ServerSocket serverSocket = new ServerSocket(8899);
            System.out.println("服务端启动");
            while (true) {
                Socket socket = serverSocket.accept();
                new Thread(() ->{
                    try {
                        ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                        ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
                        Integer id = objectInputStream.readInt();
                        User user = userService.getUserByUserId(id);
                        objectOutputStream.writeObject(user);
                        objectOutputStream.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("服务器启动失败");
        }

    }
}
