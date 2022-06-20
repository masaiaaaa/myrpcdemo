package com.sai.version2.client;


import com.sai.version1.common.User;
import com.sai.version2.service.UserService;

/**
 * @Description: TODO
 * @author: sai
 * @date: 2022年06月19日 17:59
 */
public class RPCClient {
    public static void main(String[] args) {
        ClientProxy clientProxy = new ClientProxy("127.0.0.1", 8899);
        UserService proxy = clientProxy.getProxy(UserService.class);

        User user = proxy.getUserByUserId(10);
        System.out.println("服务端得到的user为"+user);

        User user1 = User.builder().userName("张三").id(100).sex(true).build();
        Integer res = proxy.insertUserId(user1);
        System.out.println("向服务端插入数据，返回："+res);
    }
}
