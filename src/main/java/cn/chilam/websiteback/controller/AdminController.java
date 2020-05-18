package cn.chilam.websiteback.controller;

import cn.chilam.websiteback.common.entity.ResultMap;
import cn.chilam.websiteback.mapper.UserMapper;
import cn.chilam.websiteback.pojo.User;
import cn.chilam.websiteback.service.UserService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private UserService userService;


    // 获取所有用户信息
    @PostMapping("/getAllUser")
    @RequiresRoles(value = {"admin"}, logical = Logical.OR)
    public ResultMap getAllUser() {
        Map<String, Object> data = new HashMap<>();
        data.put("userInfo", userService.getAllUser());
        return ResultMap.ok().data(data);
    }

    // 删除用户
    @PostMapping("/deleteUser")
    @RequiresRoles(value = {"admin"}, logical = Logical.OR)
    public ResultMap deleteUser(Integer id) {
        if (userService.deleteUserById(id)) {
            return ResultMap.ok();
        } else {
            return ResultMap.error();
        }

    }

    // 修改用户信息
    @PostMapping("/putUser")
    @RequiresRoles(value = {"admin"}, logical = Logical.OR)
    public ResultMap putUser(Integer id, String username, String role, String phoneNum,
                             String realName, String idCard, String collegeName, String schoolNum
            , Integer gender) {
        User user = new User();
        user.setId(id);
        user.setUsername(username);
        user.setRole(role);
        user.setPhoneNum(phoneNum);
        user.setRealName(realName);
        user.setIdCard(idCard);
        user.setCollegeName(collegeName);
        user.setSchoolNum(schoolNum);
        user.setGender(gender);

        if (userService.updateUserById(user)) {
            return ResultMap.ok();
        } else {
            return ResultMap.error();
        }

    }
}
