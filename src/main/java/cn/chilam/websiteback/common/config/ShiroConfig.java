package cn.chilam.websiteback.common.config;

import cn.chilam.websiteback.util.JWTFilter;
import cn.chilam.websiteback.common.config.CustomRealm;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @program: website-back
 * @description: shiro配置类
 * @author: chilam
 * @create: 2020-03-28 15:05
 **/
@Configuration
public class ShiroConfig {


    @Bean
    public ShiroFilterFactoryBean factoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();

        // 添加自己的过滤器
        Map<String, Filter> filterMap = new LinkedHashMap<>();
        // 设置我们自己自定义的JWT过滤器
        filterMap.put("jwt", new JWTFilter());
        factoryBean.setFilters(filterMap);
        factoryBean.setSecurityManager(securityManager);
        // 设置无权限时跳转的 URL
        factoryBean.setUnauthorizedUrl("/unauthorized/无权限");
        Map<String, String> filterRuleMap = new HashMap<>();
        // 所有请求通过我们自己的 JWT Filter
        filterRuleMap.put("/**", "jwt");
        // 访问 /unauthorized/** 不通过 JWTFilter
        filterRuleMap.put("/unauthorized/**", "anon");
        factoryBean.setFilterChainDefinitionMap(filterRuleMap);
        return factoryBean;
    }


//        /**
//         * @description:
//         * @author: chilam
//         * @param: securityManager
//         * @return: org.apache.shiro.spring.web.ShiroFilterFactoryBean
//         * @date: 2020-04-02
//         */
//        @Bean
//        public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager) {
//            ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
//            // 必须设置 SecurityManager
//            shiroFilterFactoryBean.setSecurityManager(securityManager);
//            // setLoginUrl 如果不设置值，默认会自动寻找Web工程根目录下的"/login.jsp"页面 或 "/login" 映射
//            shiroFilterFactoryBean.setLoginUrl("/notLogin");
//            // 设置无权限时跳转的 url;
//            shiroFilterFactoryBean.setUnauthorizedUrl("/notRole");
//
//            // 设置拦截器
//            Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
//            // 游客，开发权限
//            filterChainDefinitionMap.put("/guest/**", "anon");
//            // 用户，需要角色权限 “student”
//            filterChainDefinitionMap.put("/student/**", "roles[student]");
//            // 教师，需要角色权限“teacher”
//            filterChainDefinitionMap.put("/teacher/**", "roles[teacher]");
//            // 管理员，需要角色权限 “admin”
//            filterChainDefinitionMap.put("/admin/**", "roles[admin]");
//            // 开放登陆接口
//            filterChainDefinitionMap.put("/login", "anon");
//            // 其余接口一律拦截
//            // 主要这行代码必须放在所有权限设置的最后，不然会导致所有 url 都被拦截
//            filterChainDefinitionMap.put("/**", "authc");
//
//            shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
//            System.out.println("Shiro拦截器工厂类注入成功");
//            return shiroFilterFactoryBean;
//        }

    /**
     * 注入 securityManager
     */
    @Bean
    public SecurityManager securityManager(CustomRealm customRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 设置自定义 realm
        securityManager.setRealm(customRealm);
        // 关闭shiro自带的session
        DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
        DefaultSessionStorageEvaluator defaultSessionStorageEvaluator =
                new DefaultSessionStorageEvaluator();
        defaultSessionStorageEvaluator.setSessionStorageEnabled(false);
        subjectDAO.setSessionStorageEvaluator(defaultSessionStorageEvaluator);
        securityManager.setSubjectDAO(subjectDAO);
        return securityManager;
    }

    /**
     * 添加注解支持
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator =
                new DefaultAdvisorAutoProxyCreator();
        // 强制使用cglib，防止重复代理和可能引起代理出错的问题
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
        return defaultAdvisorAutoProxyCreator;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }

    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

}
