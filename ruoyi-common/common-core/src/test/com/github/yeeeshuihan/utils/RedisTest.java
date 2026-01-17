package com.github.yeeeshuihan.utils;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author yeeeshuihan
 * @date 2026/1/9 8:53
 * @description
 */
@SpringBootTest
@Slf4j
public class RedisTest {

    @Autowired
    RedisUtil redisUtil;

    @Test
    public void test() {
        redisUtil.set("test", "test");
    }
}
