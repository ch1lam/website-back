package cn.chilam.websiteback.service.impl;

import cn.chilam.websiteback.mapper.UserMapper;
import cn.chilam.websiteback.pojo.User;
import cn.chilam.websiteback.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: website-back
 * @description: 登录服务实现类
 * @author: chilam
 * @create: 2020-03-04 17:33
 **/
@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> ShowUserInfo() {
        return userMapper.getUserInfoAll();
    }

    /**
     * @description: 判断是否可登录
     * @param: username
     * @param: password
     * @return: 判断结果
     * @author: chilam
     * @date: 2020-03-07
     */
    @Override
    public Boolean LoginVerify(String username, String password) {
        // 判空
        if (null == username || username.trim().length() == 0) {
            return false;
        }
        User guest = userMapper.getUserInfoByUsername(username);
        if (username.equals(guest.getUsername())) {
            return password.equals(guest.getPassword());
        }
        return true;
    }
}
