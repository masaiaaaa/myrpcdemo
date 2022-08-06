package com.sai.version1.server;

import com.sai.version1.common.User;
import com.sai.version1.service.UserService;
import org.apache.log4j.net.SocketServer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Description: TODO
 * @author: sai
 * @date: 2022年07月16日 18:47
 */
public class RPCServer {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();

        try {
            ServerSocket socketServer = new ServerSocket(8899);
            System.out.println("服务端启动了");
            while (true) {
                Socket socket = socketServer.accept();
                new Thread(() ->{
                    try {
                        ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                        ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());

                        int id = objectInputStream.readInt();
                        User user = userService.getUserById(id);

                        objectOutputStream.writeObject(user);
                        objectOutputStream.flush();

                    } catch (IOException e) {
                        e.printStackTrace();
                        System.out.println("从IO中读取数据出错");
                    }
                }).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("服务端启动失败");
        }
    }
}
