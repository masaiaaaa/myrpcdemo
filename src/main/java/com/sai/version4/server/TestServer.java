package com.sai.version4.server;

import com.sai.version4.service.BlogService;
import com.sai.version4.service.UserService;



/**
 * @Description: TODO
 * @author: sai
 * @date: 2022年08月06日 14:30
 */
public class TestServer {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        BlogService blogService = new BlogServiceImpl();

//        Map<String, Object> serviceProvide = new HashMap<>();
//        serviceProvide.put("com.ganghuan.myRPCVersion2.service.UserService",userService);
//        serviceProvide.put("com.ganghuan.myRPCVersion2.service.BlogService",blogService);
        ServiceProvider serviceProvider = new ServiceProvider();
        serviceProvider.provideServiceInterface(userService);
        serviceProvider.provideServiceInterface(blogService);

        RPCServer RPCServer = new NettyRPCServer(serviceProvider);
        RPCServer.start(8899);
    }
}
