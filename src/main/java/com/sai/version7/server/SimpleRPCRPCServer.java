package com.sai.version7.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Description: TODO
 * @author: sai
 * @date: 2022年08月06日 14:35
 */
public class SimpleRPCRPCServer implements RPCServer {


    private ServiceProvider serviceProvider;

    public SimpleRPCRPCServer(ServiceProvider serviceProvider) {
        this.serviceProvider = serviceProvider;
    }

    @Override
    public void start(int port) {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("服务端启动了");

            while(true){
                Socket socket = serverSocket.accept();
                new Thread(new WorkThread(socket, serviceProvider)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("服务器启动失败");
        }

    }

    @Override
    public void stop() {

    }
}
