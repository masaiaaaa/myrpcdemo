package com.sai.version2.server;

import com.sai.version2.common.RPCRequest;
import com.sai.version2.common.RPCResponse;
import com.sai.version2.service.UserService;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Description: TODO
 * @author: sai
 * @date: 2022年08月06日 11:35
 */
public class RPCServer {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();

        try {
            ServerSocket serverSocket = new ServerSocket(8899);
            System.out.println("服务端启动了");

            while (true) {
                Socket socket = serverSocket.accept();
                new Thread(() -> {
                    try {
                        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

                        //读取客户端传来的request
                        RPCRequest request = (RPCRequest)ois.readObject();

                        //反射调用对应方法
                        Method method = userService.getClass().getMethod(request.getMethodName(), request.getParamsTypes());
                        Object invoke = method.invoke(userService, request.getParams());

                        //封装，写入response对象
                        oos.writeObject(RPCResponse.success(invoke));
                        oos.flush();
                    } catch (IOException | ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                        System.out.println("IO读取数据错误");
                    }
                }).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
