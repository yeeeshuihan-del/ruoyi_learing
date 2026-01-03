package com.github.yeeeshuihan.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.yeeeshuihan.entity.GenTable;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import java.util.List;

/**
 * @author zengxiangbao
 * @date 2026/1/3 19:19
 * @description
 */
@Mapper
public interface GenTableMapper extends BaseMapper<GenTable> {

    GenTable selectTableByName(@Param("tableName") String tableName);
}
