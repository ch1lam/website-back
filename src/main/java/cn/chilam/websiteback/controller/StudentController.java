package cn.chilam.websiteback.controller;

import cn.chilam.websiteback.common.entity.ResultMap;
import cn.chilam.websiteback.service.UserService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: website-back
 * @description: 学生权限api
 * @author: chilam
 * @create: 2020-04-02 14:03
 **/
@RestController
@RequestMapping("/student")
@CrossOrigin
public class StudentController {
    @Autowired
    UserService userService;

    @RequestMapping(value = "/getMessage", method = RequestMethod.GET)
    @RequiresRoles(value = {"student", "teacher", "admin"}, logical = Logical.OR)
    public ResultMap getMessage() {
        return ResultMap.ok();
    }


    @PostMapping("/getUserInfo")
    @RequiresRoles(value = {"student", "teacher", "admin"}, logical = Logical.OR)
    public ResultMap getUserInfo(@RequestParam("username") String username) {
        Map<String, Object> data = new HashMap<>();
        data.put("userInfo", userService.getUserInfoByName(username));
        return ResultMap.ok().data(data);
    }
}
