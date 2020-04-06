package cn.chilam.websiteback.common.config;

import cn.chilam.websiteback.common.entity.JWTToken;
import cn.chilam.websiteback.mapper.UserMapper;
import cn.chilam.websiteback.util.JWTUtil;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * @program: website-back
 * @description: 自定义realm类
 * @author: chilam
 * @create: 2020-03-30 23:32
 **/
@Component
public class CustomRealm extends AuthorizingRealm {
    private UserMapper userMapper;

    @Autowired
    private void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
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
        String token = (String) authenticationToken.getCredentials();
        // 解密获得 username，用于和数据库进行对比
        String username = JWTUtil.getUsername(token);
        if (username == null || !JWTUtil.verify(token, username)) {
            throw new AuthenticationException("token认证失败");
        }
        String password = userMapper.getPasswordByUsername(username);
        if (password == null) {
            throw new AuthenticationException("该用户不存在！");
        }
        return new SimpleAuthenticationInfo(token, token, "MyRealm");


//        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
//        // 从数据库获取对应用户名密码的用户
//        String password = userMapper.getPasswordByUsername(token.getUsername());
//        if (null == password) {
//            throw new AccountException("用户名不正确");
//        } else if (!password.equals(new String((char[]) token.getCredentials()))) {
//            throw new AccountException("密码不正确");
//        }
//        return new SimpleAuthenticationInfo(token.getPrincipal(), password, getName());
    }


    /**
     * @description: 检测用户权限
     * @author: chilam
     * @param: principalCollection
     * @return: info
     * @date: 2020/3/30
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("——权限认证——");
        String username=JWTUtil.getUsername(principalCollection.toString());
        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
        // 获得该用户角色
        String role = userMapper.getRoleByUsername(username);
        Set<String> roleSet=new HashSet<>();
        // 需要将 role 封装到 Set 作为 info.setRole(), info.setStringPermissions() 参数
        roleSet.add(role);
        // 设置该用户拥有的角色
        info.setRoles(roleSet);
        return info;



//        String username = (String) SecurityUtils.getSubject().getPrincipal();
//        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
//        // 获得该用户角色
//        String role = userMapper.getRoleByUsername(username);
//        // role 不重复
//        Set<String> set = new HashSet<>();
//        // 需要将 role 封装到 Set 作为 info.setRoles() 的参数
//        set.add(role);
//        // 设置该用户拥有的角色
//        info.setRoles(set);
//        return info;
    }


}
