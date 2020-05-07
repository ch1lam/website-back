package cn.chilam.websiteback.service;

import cn.chilam.websiteback.pojo.User;

import java.util.List;

/**
 * @program: website-back
 * @description: 用户信息服务
 * @author: chilam
 * @create: 2020-03-04 23:37
 **/
public interface UserService {
    User getUserInfoByName(String name);
    List<User> getAllUser();
}
