package com.sai.version2.server;

import com.sai.version1.common.User;
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
                        //读取客户端传过来的request
                        RPCRequest request = (RPCRequest)objectInputStream.readObject();
                        //反射调用对应方法
                        Method method = userService.getClass().getMethod(request.getMethodName(), request.getParamTypes());
                        Object object = method.invoke(userService, request.getParams());
                        //写入到response对象
                        objectOutputStream.writeObject(RPCResponse.success(object));
                        objectOutputStream.flush();
                    } catch (IOException | ClassNotFoundException | NoSuchMethodException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
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
