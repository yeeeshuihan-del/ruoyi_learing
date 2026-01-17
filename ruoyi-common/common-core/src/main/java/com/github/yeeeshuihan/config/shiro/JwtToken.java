package com.github.yeeeshuihan.config.shiro;

import lombok.Data;

/**
 * @author yeeeshuihan
 * @date 2026/1/12 12:45
 * @description
 */
@Data
public class JwtToken implements org.apache.shiro.authc.AuthenticationToken{

    private Object token;

    public JwtToken(String principal) {
        this.token = principal;
    }

    @Override
    public Object getPrincipal() {
        return this.token;
    }

    @Override
    public Object getCredentials() {
        return this.token;
    }
}
