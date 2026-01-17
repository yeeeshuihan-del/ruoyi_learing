package com.github.yeeeshuihan.utils;

import cn.hutool.jwt.JWTUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yeeeshuihan
 * @date 2026/1/17 10:36
 * @description Jwt工具
 */
public class JwtUtil {

    private static final String DEFAULT_SECRET = "yeeeshuihan";

    public String createJwt(String userId) {
        Map<String, Object> claims = new HashMap<>();
        return createJwt(userId, DEFAULT_SECRET);
    }

    public static String createJwt(String userId, String secret) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", userId);
        return JWTUtil.createToken(claims, secret.getBytes());
    }

    /**
     * 获取userId
     */
    public static String getUserId(String jwt) {
        if (jwt == null) {
            return null;
        }
        if (!JWTUtil.verify(jwt, DEFAULT_SECRET.getBytes())) {
            return null;
        }
        return JWTUtil.parseToken(jwt).getPayload("userId").toString();
    }
}
