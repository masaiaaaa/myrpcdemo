package com.sai.version4.service;


import com.sai.version4.common.User;

public interface UserService {
    //客户端通过这接口调用服务端的实现
    User getUserByUserId(Integer id);
    //给这个服务增加一个功能
    Integer insertUserId(User user);
}
