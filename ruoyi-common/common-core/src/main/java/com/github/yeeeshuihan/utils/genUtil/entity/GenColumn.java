package com.github.yeeeshuihan.utils.genUtil.entity;

import lombok.Data;

/**
 * @author yeeeshuihan
 * @date 2026/1/3 17:50
 * @description
 */
@Data
public class GenColumn {

    String columnComment;

    String javaField;

    String javaType;

    String columnName;

    String isPk;

    String queryType = "EQ";
}
