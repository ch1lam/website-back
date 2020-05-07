package cn.chilam.websiteback.controller;

import cn.chilam.websiteback.common.entity.ResultMap;
import cn.chilam.websiteback.service.UserService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: website-back
 * @description: 教师权限api
 * @author: chilam
 * @create: 2020-04-02 14:04
 **/
@RestController
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    UserService userService;

    @PostMapping("/getAllUser")
    @RequiresRoles(value = {"teacher", "admin"}, logical = Logical.OR)
    public ResultMap getAllUser() {
        Map<String, Object> data = new HashMap<>();
        data.put("userInfo", userService.getAllUser());
        return ResultMap.ok().data(data);
    }
}
