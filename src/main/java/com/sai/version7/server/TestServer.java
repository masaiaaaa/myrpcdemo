package com.sai.version7.server;

import com.sai.version7.service.BlogService;
import com.sai.version7.service.UserService;



/**
 * @Description: TODO
 * @author: sai
 * @date: 2022年08月06日 14:30
 */
public class TestServer {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        BlogService blogService = new BlogServiceImpl();

        ServiceProvider serviceProvider = new ServiceProvider("127.0.0.1", 8899);
        serviceProvider.provideServiceInterface(userService);
        serviceProvider.provideServiceInterface(blogService);

        RPCServer RPCServer = new NettyRPCServer(serviceProvider);
        RPCServer.start(8899);
    }
}
