package com.sai.version4.server;



import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Description: TODO
 * @author: sai
 * @date: 2022年08月06日 15:07
 */
public class ThreadPoolRPCRPCServer implements RPCServer {
    private final ThreadPoolExecutor threadPool;
    private ServiceProvider serviceProvider;

    public ThreadPoolRPCRPCServer(ServiceProvider serviceProvider){
        this.threadPool = new ThreadPoolExecutor(
                Runtime.getRuntime().availableProcessors(),
                1000,
                60,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(100)
        );
        this.serviceProvider = serviceProvider;
    }
    public ThreadPoolRPCRPCServer(ServiceProvider serviceProvider,
                                  int corePoolSize,
                                  int maximumPoolSize,
                                  long keepAliveTime,
                                  TimeUnit unit,
                                  BlockingQueue<Runnable> workQueue){
        this.threadPool = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
        this.serviceProvider = serviceProvider;
    }
    @Override
    public void start(int port) {
        System.out.println("服务端启动了");
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            while (true){
                Socket socket = serverSocket.accept();
                threadPool.execute(new WorkThread(socket, serviceProvider));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void stop() {

    }
}
