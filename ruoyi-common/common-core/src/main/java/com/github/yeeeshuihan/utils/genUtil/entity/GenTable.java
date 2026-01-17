package com.github.yeeeshuihan.utils.genUtil.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.List;

/**
 * @author yeeeshuihan
 * @date 2026/1/3 17:52
 * @description
 */
@Data
@TableName("information_schema.tables")
public class GenTable {

    String tableName;

    String tableComment;

    List<GenColumn> columns;
}
