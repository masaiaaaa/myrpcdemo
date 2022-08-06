package com.sai.version3.client;


import com.sai.version3.common.User;
import com.sai.version3.service.UserService;

/**
 * @Description: TODO
 * @author: sai
 * @date: 2022年08月06日 13:58
 */
public class RPCClient {
    public static void main(String[] args) {
        ClientProxy clientProxy = new ClientProxy("127.0.0.1", 8899);
        UserService proxy = clientProxy.getProxy(UserService.class);
        //服务的方法1
        User userByUserId = proxy.getUserByUserId(10);
        System.out.println("从服务端得到的user为：" + userByUserId);
        //服务的方法2
        User user = User.builder().userName("张三").id(100).sex(true).build();
        Integer integer = proxy.insertUserId(user);
        System.out.println("向服务端插入数据："+integer);
    }
}
