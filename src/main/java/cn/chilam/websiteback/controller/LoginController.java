package cn.chilam.websiteback.controller;

import cn.chilam.websiteback.common.entity.JWTResponseData;
import cn.chilam.websiteback.common.entity.JWTResult;
import cn.chilam.websiteback.common.entity.JWTSubject;
import cn.chilam.websiteback.service.LoginService;
import cn.chilam.websiteback.util.JWTUtil;
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

    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Object login(@RequestParam(value = "username") String username, @RequestParam(value = "password")
            String password) {
        JWTResponseData responseData = null;
        // 认证用户信息
        if (loginService.LoginVerify(username, password)) {
            JWTSubject subject = new JWTSubject(username);
            String jwtToken = JWTUtil.createJWT(UUID.randomUUID().toString(), "test-jwt",
                    JWTUtil.generalSubject(subject), 60 * 60 * 1000);
            responseData = new JWTResponseData();
            responseData.setCode(200);
            responseData.setData(null);
            responseData.setMsg("登录成功");
            responseData.setToken(jwtToken);
        } else {
            responseData = new JWTResponseData();
            responseData.setCode(500);
            responseData.setData(null);
            responseData.setMsg("登录失败");
            responseData.setToken(null);
        }
        return responseData;
    }

    @ResponseBody
    @RequestMapping("/testAll")
    public Object testAll(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        JWTResult result = JWTUtil.validateJWT(token);

        JWTResponseData responseData = new JWTResponseData();

        if (result.isSuccess()) {
            responseData.setCode(200);
            responseData.setData(result.getClaims().getSubject());

            // 重新生成token，就是为了重置token的有效期
            String newToken = JWTUtil.createJWT(result.getClaims().getId(), result.getClaims().getIssuer(), result
                    .getClaims().getSubject(), 30 * 60 * 1000);
            responseData.setToken(newToken);
            return responseData;
        } else {
            responseData.setCode(500);
            responseData.setMsg("用户未登录");
            return responseData;
        }


    }


}
