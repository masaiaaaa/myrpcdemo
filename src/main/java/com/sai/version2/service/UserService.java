package com.sai.version2.service;

import com.sai.version1.common.User;

public interface UserService {
    // 客户端通过这个接口调用服务端的实现类
    User getUserByUserId(Integer id);
    //给这个服务加一个功能
    Integer insertUserId(User user);

}
