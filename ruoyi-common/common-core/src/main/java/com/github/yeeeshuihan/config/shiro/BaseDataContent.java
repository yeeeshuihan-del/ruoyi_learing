package com.github.yeeeshuihan.config.shiro;

import com.github.yeeeshuihan.common.constant.TokenConstant;
import com.github.yeeeshuihan.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author yeeeshuihan
 * @date 2026/1/17 10:25
 * @description
 */
@Component
public class BaseDataContent {

    @Autowired
    private RedisUtil redisUtil;


    /**
     * 检查token是否有效
     */
    public String getToken(String token) {
        String redisKey = TokenConstant.CACHE_TOKEN_PREFIX + token;
        if (redisUtil.hasKey(redisKey)) {
            return redisUtil.get(redisKey).toString();
        }
        return null;
    }
}
