package cn.chilam.websiteback.controller;

import cn.chilam.websiteback.common.entity.ResultMap;
import cn.chilam.websiteback.mapper.UserMapper;
import cn.chilam.websiteback.service.LoginService;
import cn.chilam.websiteback.util.JWTUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
            return ResultMap.ok().data("token", JWTUtil.createToken(UUID.randomUUID().toString(),
                    username));
        }


//        // 从SecurityUtils里创建一个subject
//        Subject subject = SecurityUtils.getSubject();
//        // 在认证提交前准备token（令牌）
//        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
//        // 执行认证登陆
//        subject.login(token);
//        // 根据权限，指定返回数据
//        String role = userMapper.getRoleByUsername(username);
//        if ("user".equals(role)) {
//            return ResultMap.ok().message("欢迎登陆！");
//        } else if ("admin".equals(role)) {
//            return ResultMap.ok().message("欢迎来到管理员页面");
//        }
//        return ResultMap.error().message("权限错误");
    }
//
//
//    @ResponseBody
//    @RequestMapping(value = "/login1", method = RequestMethod.POST)
//    public Object login1(@RequestParam(value = "username") String username, @RequestParam(value =
//            "password")
//            String password) {
//        JWTResponseData responseData = null;
//        // 认证用户信息
//        if (loginService.LoginVerify(username, password)) {
//            JWTSubject subject = new JWTSubject(username);
//            String jwtToken = JWTUtil.createJWT(UUID.randomUUID().toString(), "test-jwt",
//                    JWTUtil.generalSubject(subject), 60 * 60 * 1000);
//            responseData = new JWTResponseData();
//            responseData.setCode(200);
//            responseData.setData(null);
//            responseData.setMsg("登录成功");
//            responseData.setToken(jwtToken);
//        } else {
//            responseData = new JWTResponseData();
//            responseData.setCode(500);
//            responseData.setData(null);
//            responseData.setMsg("登录失败");
//            responseData.setToken(null);
//        }
//        return responseData;
//    }
//
//    @ResponseBody
//    @RequestMapping("/testAll")
//    public Object testAll(HttpServletRequest request) {
//        String token = request.getHeader("Authorization");
//        JWTResult result = JWTUtil.validateJWT(token);
//
//        JWTResponseData responseData = new JWTResponseData();
//
//        if (result.isSuccess()) {
//            responseData.setCode(200);
//            responseData.setData(result.getClaims().getSubject());
//
//            // 重新生成token，就是为了重置token的有效期
//            String newToken = JWTUtil.createJWT(result.getClaims().getId(),
//                    result.getClaims().getIssuer(), result
//                            .getClaims().getSubject(), 30 * 60 * 1000);
//            responseData.setToken(newToken);
//        } else {
//            responseData.setCode(500);
//            responseData.setMsg("用户未登录");
//        }
//        return responseData;
//
//
//    }
//

}
