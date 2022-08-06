package com.sai.version1.server;

import com.sai.version1.common.User;
import com.sai.version1.service.UserService;

import java.util.Random;
import java.util.UUID;

/**
 * @Description: TODO
 * @author: sai
 * @date: 2022年07月16日 18:35
 */
public class UserServiceImpl implements UserService {
    @Override
    public User getUserById(Integer id) {
        System.out.println("客户端查询了id为："+id+"的用户");
        //模拟从数据库中取得一个用户
        Random random = new Random();
        User user = User.builder().userName(UUID.randomUUID().toString())
                .id(id)
                .sex(random.nextBoolean())
                .build();
        return user;
    }
}
