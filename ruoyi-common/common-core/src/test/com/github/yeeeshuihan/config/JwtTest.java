package com.github.yeeeshuihan.config;

import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTHeader;
import cn.hutool.jwt.JWTUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Map;

/**
 * @author yeeeshuihan
 * @date 2026/1/12 11:40
 * @description
 */
@Slf4j
public class JwtTest {

   @Test
   public void test() {
       // 密钥
       byte[] key = "1234567890".getBytes();

       String token = JWT.create()
               .setPayload("sub", "1234567890")
               .setPayload("name", "looly")
               .setPayload("admin", true)
               .setKey(key)
               .sign();
       log.info("token: {}", token);
       String rightToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6Imxvb2x5IiwiYWRtaW4iOnRydWV9.Tvz26gnquEz-1fh4bDOA6zVx_eF53hdDz_mQvp6kJbs";

       JWT jwt = JWT.of(rightToken);
       Map<String, Object> header = jwt.getHeaders();
//       jwt.getHeader(JWTHeader.TYPE);
//       jwt.getHeader(JWTHeader.ALGORITHM);
//       jwt.getPayload("sub");
//       jwt.getPayload("name");
//       jwt.getPayload("admin");
       Map<String, Object> payloads = jwt.getPayloads();
       log.info("payloads: {}", payloads);
       log.info("header: {}", header);

       log.info("verify: {}", JWT.of(rightToken).setKey(key).verify());

   }
}
