package com.github.yeeeshuihan.utils.genUtil.model;

import lombok.Data;

import java.io.File;

/**
 * @author yeeeshuihan
 * @date 2026/1/17 11:35
 * @description
 */
@Data
public class BasicParam {
    final String url = "jdbc:mysql://localhost/learing-ruoyi?characterEncoding=UTF-8&useUnicode=true&useSSL=false&tinyInt1isBit=false&allowPublicKeyRetrieval=true&serverTimezone=Asia/Shanghai&rewriteBatchedStatements=true&connectTimeout=300000&socketTimeout=300000";
    final String username = "root";
    final String password = "123456";
    final String outputDir = "D:\\下载\\chrome下载" + File.separator +"";
    final String author = "yeeeshuihan";
    final String commentDate = "yyyy-MM-dd";
    final boolean disableOpenDir = false;
    final String parent = "com.github.yeeeshuihan";
}