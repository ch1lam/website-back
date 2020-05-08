package cn.chilam.websiteback.service.impl;

import cn.chilam.websiteback.mapper.UserMapper;
import cn.chilam.websiteback.pojo.User;
import cn.chilam.websiteback.service.UserService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: website-back
 * @description: 用户信息服务实现类
 * @author: chilam
 * @create: 2020-03-04 23:39
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public User getUserInfoByName(String username) {
        return userMapper.selectByName(username);
    }

    @Override
    public List<User> getAllUser() {
//        PageHelper.startPage(1,10);
        return userMapper.getAllUser();
    }

    @Override
    public boolean updatePassword(String username, String password, String newPassword) {
        if (userMapper.getPasswordByUsername(username).equals(password)) {
            userMapper.updatePassword(username, newPassword);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String getAvatarUrl(String username) {
        return userMapper.getAvatarUrl(username);
    }
}
