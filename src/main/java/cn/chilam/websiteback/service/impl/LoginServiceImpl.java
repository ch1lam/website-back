package cn.chilam.websiteback.service.impl;

import cn.chilam.websiteback.common.entity.ResultMap;
import cn.chilam.websiteback.mapper.UserMapper;
import cn.chilam.websiteback.pojo.User;
import cn.chilam.websiteback.service.LoginService;
import cn.chilam.websiteback.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;


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

    /**
     * @description: 判断是否可登录
     * @param: username
     * @param: password
     * @return: 判断结果
     * @author: chilam
     * @date: 2020-03-07
     */
    @Override
    public Boolean loginVerify(String username, String password) {
        // 判空
        if (null == username || username.trim().length() == 0) {
            return false;
        }
        return password.equals(userMapper.getPasswordByUsername(username));
    }


    /**
     * @description: 校验并刷新token
     * @author: chilam
     * @param: token
     * @return: cn.chilam.websiteback.common.entity.ResultMap
     * @date: 2020-04-04
     */
    @Override
    public ResultMap refreshToken(String token) {
        try {
            String newToken = JWTUtil.createToken(JWTUtil.getKeyId(token),
                    JWTUtil.getUsername(token));
            return ResultMap.ok().data("token", newToken);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultMap.error().message("刷新token失败");
        }
    }


    /**
     * @description: 注册服务
     * @author: chilam
     * @param: username
     * @param: password
     * @param: phoneNum
     * @return: 0：表示注册失败 1：表示注册成功 2：表示用户名已存在
     * @date: 2020-04-09
     */
    @Override
    public int resign(String username, String password, String phoneNum) {
        if (1 == userMapper.isExistsByUsername(username)) {
            return 2;
        }
        User user = new User(username, password, "admin", phoneNum);
        try {
            userMapper.insertSelective(user);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;

        }

    }

}
