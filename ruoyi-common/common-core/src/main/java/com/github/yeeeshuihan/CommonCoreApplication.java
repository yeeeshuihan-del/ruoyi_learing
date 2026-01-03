package com.github.yeeeshuihan;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * @author zengxiangbao
 * @date 2026/1/3 13:49
 * @description
 */
@SpringBootApplication
@MapperScan("com.github.yeeeshuihan.mapper")
public class CommonCoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommonCoreApplication.class, args);
    }
}
