package com.sai.version4.client;

import com.sai.version4.common.User;
import com.sai.version4.service.UserService;

/**
 * @Description: TODO
 * @author: sai
 * @date: 2022年08月06日 15:59
 */
public class TestClient {
    public static void main(String[] args) {
        SimpleRPCClient simpleRPCClient = new SimpleRPCClient("127.0.0.1", 8899);

        RPCClientProxy rpcClientProxy = new RPCClientProxy(simpleRPCClient);

        UserService userService = rpcClientProxy.getProxy(UserService.class);

        User user = userService.getUserByUserId(10);

    }
}
