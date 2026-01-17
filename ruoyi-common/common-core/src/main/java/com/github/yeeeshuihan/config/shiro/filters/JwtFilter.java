package com.github.yeeeshuihan.config.shiro.filters;

import cn.hutool.jwt.JWTUtil;
import com.github.yeeeshuihan.config.shiro.JwtToken;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;

/**
 * @author yeeeshuihan
 * @date 2026/1/12 9:39
 * @description 只检查是否有token提交realm
 */
@Slf4j
public class JwtFilter extends BasicHttpAuthenticationFilter {

    @Override
    public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        return executeLogin(request, response);
    }

    @Override
    public boolean executeLogin(ServletRequest request, ServletResponse response) {
        if (request instanceof HttpServletRequest) {
            HttpServletRequest httpServletRequest = (HttpServletRequest) request;
            String token = httpServletRequest.getHeader("token");
            if (token != null) {
                log.info("token: {}", token);
//                JWTUtil.verify(token, "1234567890".getBytes());
//                String principal = JWTUtil.parseToken(token).getPayload("user").toString();
//                JwtToken jwtToken = new JwtToken(principal);
                JwtToken jwtToken = new JwtToken(token);
                try {
                    getSubject(request, response).login(jwtToken); //提交realm
                    return true;
                } catch (Exception e) {
                    log.info("认证失败");
                    responseError(response, "认证失败");
                }
            } else {
                responseError(response, "认证失败");
            }
            return false;
        }

        return false;
    }

    public void responseError(ServletResponse response, String message) {
        try {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            response.getWriter().println(message);
            response.getWriter().flush();
        } catch (Exception e) {
            log.error("response error", e);
        }
    }
}
