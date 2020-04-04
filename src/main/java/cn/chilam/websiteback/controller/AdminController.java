package cn.chilam.websiteback.controller;

import cn.chilam.websiteback.common.entity.ResultMap;
import cn.chilam.websiteback.mapper.UserMapper;
import cn.chilam.websiteback.pojo.User;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @program: website-back
 * @description: 管理员权限api
 * @author: chilam
 * @create: 2020-04-02 14:04
 **/
@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserMapper userMapper;

    @RequiresRoles("admin")
    @GetMapping("/test")
    public ResultMap getUser() {
        List<User> AllInfo = userMapper.getAllUser();
        return ResultMap.ok().data("AllInfo", AllInfo);
    }
}
