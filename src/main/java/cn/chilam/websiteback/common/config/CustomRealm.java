package cn.chilam.websiteback.common.config;

import cn.chilam.websiteback.mapper.UserMapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

/**
 * @program: website-back
 * @description: 自定义realm类
 * @author: chilam
 * @create: 2020-03-30 23:32
 **/
public class CustomRealm extends AuthorizingRealm {
    private UserMapper userMapper;

    @Autowired
    private void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    /**
     * @description: 获取身份验证信息，最终通过 Realm 来获取应用程序中的用户、角色及权限信息
     * @author: chilam
     * @param: authenticationToken 用户身份信息
     * @return: 返回了封装了用户信息的 AuthorizationInfo 的实例
     * @date: 2020/3/30
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("——身份认证——");
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        // 从数据库获取对应用户名密码的用户
        String password = userMapper.getPasswordByUsername(token.getUsername());
        if (null == password) {
            throw new AccountException("用户名不正确");
        } else if (!password.equals(new String((char[]) token.getCredentials()))) {
            throw new AccountException("密码不正确");
        }
        return new SimpleAuthenticationInfo(token.getPrincipal(), password, getName());
    }

    
    /**
     * @description: 获取授权信息
     * @author: chilam
     * @param: principalCollection
     * @return: info
     * @date: 2020/3/30
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("——权限认证——");
        String username = (String) SecurityUtils.getSubject().getPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        // 获得该用户角色
        String role = userMapper.getRoleByUsername(username);
        // role 不重复
        Set<String> set = new HashSet<>();
        // 需要将 role 封装到 Set 作为 info.setRoles() 的参数
        set.add(role);
        // 设置该用户拥有的角色
        info.setRoles(set);
        return info;
    }


}
