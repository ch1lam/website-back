package cn.chilam.websiteback.common.entity;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @program: website-back
 * @description:
 * @author: chilam
 * @create: 2020-04-03 16:24
 **/
public class JWTToken implements AuthenticationToken {
    private String token;

    public JWTToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
