package cn.chilam.websiteback.controller;

import cn.chilam.websiteback.common.entity.ResultMap;
import cn.chilam.websiteback.mapper.UserMapper;
import cn.chilam.websiteback.service.LoginService;
import cn.chilam.websiteback.util.JWTUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


/**
 * @program: website-back
 * @description: 登录控制器
 * @author: chilam
 * @create: 2020-03-01 22:58
 **/
@RestController
@CrossOrigin
public class LoginController {
    @Autowired
    private LoginService loginService;

    @Autowired
    private UserMapper userMapper;

    @RequestMapping(value = "/notLogin", method = RequestMethod.GET)
    public ResultMap notLogin() {
        return ResultMap.ok().message("你尚未登陆！");
    }

    @RequestMapping(value = "/notRole", method = RequestMethod.GET)
    public ResultMap notRole() {
        return ResultMap.ok().message("你没有权限！");
    }

    public ResultMap logout() {
        Subject subject = SecurityUtils.getSubject();
        // 注销
        subject.logout();
        return ResultMap.ok().message("成功注销！");
    }


    // TODO 未封装于service层
    @PostMapping("login")
    public ResultMap login(@RequestParam("username") String username,
                           @RequestParam("password") String password) {
        String realPassword = userMapper.getPasswordByUsername(username);
        if (realPassword == null) {
            return ResultMap.error().message("用户名错误");
        } else if (!realPassword.equals(password)) {
            return ResultMap.error().message("密码错误");
        } else {
            Map<String, Object> data = new HashMap<>();
            data.put("token", JWTUtil.createToken(UUID.randomUUID().toString(),
                    username));
            return ResultMap.ok().data(data);
        }

    }

}
