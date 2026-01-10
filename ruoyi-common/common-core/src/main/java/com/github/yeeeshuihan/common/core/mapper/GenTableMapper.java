package com.github.yeeeshuihan.common.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.yeeeshuihan.common.core.entity.GenTable;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author zengxiangbao
 * @date 2026/1/3 19:19
 * @description
 */
@Mapper
public interface GenTableMapper extends BaseMapper<GenTable> {

    GenTable selectTableByName(@Param("tableName") String tableName);
}
