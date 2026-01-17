package com.github.yeeeshuihan.utils.genUtil.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.yeeeshuihan.utils.genUtil.entity.GenTable;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author yeeeshuihan
 * @date 2026/1/3 19:19
 * @description
 */
@Mapper
public interface GenTableMapper extends BaseMapper<GenTable> {

    GenTable selectTableByName(@Param("tableName") String tableName);
}
