package cn.chilam.websiteback.util;

import cn.chilam.websiteback.common.entity.JWTToken;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * @program: website-back
 * @description: 自定义JWT过滤器
 * @author: chilam
 * @create: 2020-04-03 15:43
 **/
public class JWTFilter extends BasicHttpAuthenticationFilter {
    private Logger logger = LoggerFactory.getLogger(this.getClass());


    /**
     * @description: 检查是 token 是否通过
     * @author: chilam
     * @param: request
     * @param: response
     * @param: mappedValue
     * @return: boolean
     * @date: 2020-04-04
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response,
                                      Object mappedValue) throws UnauthorizedException {
        // 判断请求的请求头是否带上Token
        if (((HttpServletRequest) request).getHeader("Token") != null) {
            // 如果存在，则进入 executeLogin 方法执行登入，检查 token 是否正确
            try {
                executeLogin(request, response);
                return true;
            } catch (Exception e) {
                // token 错误
                responseError(response, e.getMessage());
            }
        }
        // 如果请求头不存在 Token 则可能是执行登陆操作或游客状态访问，无需检查token，直接返回true
        return true;
    }


    /**
     * @description: 检查 token 是否正确
     * @author: chilam
     * @param: request
     * @param: response
     * @return: boolean
     * @date: 2020-04-04
     */
    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String token = httpServletRequest.getHeader("Token");
        JWTToken jwtToken = new JWTToken(token);
        // 提交给 realm 进行登入，如果错误他会跑出异常并被捕获
        getSubject(request, response).login(jwtToken);
        // 如果没有抛出异常则代表登入成功，返回true
        return true;
    }


    private void responseError(ServletResponse response, String message) {
        try {
            HttpServletResponse httpServletResponse = (HttpServletResponse) response;
            // 设置编码，费则中文字符在重定向时会变为空字符串
            message = URLEncoder.encode(message, "UTF-8");
            httpServletResponse.sendRedirect("/unauthorized" + message);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }
}
