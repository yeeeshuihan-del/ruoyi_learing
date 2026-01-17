package com.github.yeeeshuihan.config.shiro;

import com.github.yeeeshuihan.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author yeeeshuihan
 * @date 2026/1/12 14:20
 * @description
 */
@Slf4j
@Component
public class ShiroRealm extends org.apache.shiro.realm.AuthorizingRealm{

    @Autowired
    BaseDataContent baseDataContent;

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        JwtToken jwtToken = (JwtToken) authenticationToken;
        String token = jwtToken.getPrincipal().toString();
        //判断是否存在该token
        if (baseDataContent.getToken(token) != null) {
            String userId = JwtUtil.getUserId(token);
            //获取用户的Login信息
//            Login
        }
        return null;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }
}
