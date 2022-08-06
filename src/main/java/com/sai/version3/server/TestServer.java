package com.sai.version3.server;

import com.sai.version3.service.BlogService;
import com.sai.version3.service.UserService;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: TODO
 * @author: sai
 * @date: 2022年08月06日 14:30
 */
public class TestServer {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        BlogService blogService = new BlogServiceImpl();

        Map<String, Object> serviceProvide = new HashMap<>();
//        serviceProvide.put("com.sai.version3.service.UserService",userService);
//        serviceProvide.put("com.sai.version3.service.BlogService",blogService);

        ServiceProvider serviceProvider = new ServiceProvider();
        serviceProvider.provideServiceInterface(userService);
        serviceProvider.provideServiceInterface(blogService);
        RPCServer server = new ThreadPoolRPCRPCServer(serviceProvider);
        server.start(8899);
    }
}
