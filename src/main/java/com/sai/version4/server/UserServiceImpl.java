package com.sai.version4.server;



import com.sai.version4.common.User;
import com.sai.version4.service.UserService;

import java.util.Random;
import java.util.UUID;

/**
 * @Description: TODO
 * @author: sai
 * @date: 2022年08月06日 11:31
 */
public class UserServiceImpl implements UserService {
    @Override
    public User getUserByUserId(Integer id) {
        System.out.println("客户端查询了"+id+"用户");
        //模拟从数据库去用户
        Random random = new Random();
        User user = User.builder().userName(UUID.randomUUID().toString())
                .id(id).sex(random.nextBoolean())
                .build();
        return user;
    }

    @Override
    public Integer insertUserId(User user) {
        System.out.println("插入数据成功"+user);
        return 1;
    }
}
