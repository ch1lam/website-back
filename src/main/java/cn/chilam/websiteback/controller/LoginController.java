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


    @PostMapping("/login")
    public ResultMap login(@RequestParam("username") String username,
                           @RequestParam("password") String password) {
        if (loginService.loginVerify(username, password)) {
            Map<String, Object> data = new HashMap<>();
            data.put("token", JWTUtil.createToken(UUID.randomUUID().toString(),
                    username));
            return ResultMap.ok().data(data);
        } else {
            return ResultMap.error().message("用户名或密码错误");
        }
    }

    @PostMapping("/resign")
    public ResultMap resign(String username, String password, String phoneNum) {
        switch (loginService.resign(username, password, phoneNum)) {
            case 0:
                return ResultMap.error().message("注册失败");
            case 1:
                return ResultMap.ok().message("注册成功");
            case 2:
                return ResultMap.error().message("用户名重复");
            default:
                return ResultMap.error().message("未知错误");
        }


    }

}
