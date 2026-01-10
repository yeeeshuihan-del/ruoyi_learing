<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${packageName}.${moduleName}.mapper.${ClassName}Mapper">

    <!-- 通用查询映射结果 -->
    <resultMap id="${className}VOMap" type="${packageName}.${moduleName}.model.${ClassName}VO">
        <#list columns as column>
            ##生成公共字段
            <result column="${column.columnName!}" property="${column.javaField!}" />
        </#list>
    </resultMap>

    <!-- 通用查询结果列 -->
    <sql id="${ClassName}_column">
        <#assign count = 0>
        <#list columns as column>
            <#assign count = count + 1>
            <#if count == columns?size>
                ${tableName!}.${column.columnName!}
            <#else>
                ${tableName!}.${column.columnName!},
            </#if>
        </#list>
    </sql>

    <sql id="${ClassName}_Query_List_Sharing">
        <trim prefix="WHERE" prefixOverrides="AND|OR">
            <#list columns as column>
                <#if column.javaField == "validTag">
                    AND ${tableName!}.${column.columnName!} = '1'
                <#elseif column.javaType == "String">
                    <if test="param.${column.javaField!} != null and param.${column.javaField!} != ''">
                        AND ${tableName!}.${column.columnName!} = <#noparse>#{param.</#noparse>${column.javaField!}}
                    </if>
                <#elseif column.javaType == "Date">
                    <if test="param.${column.javaField!}Begin != null">
                        AND ${tableName!}.${column.columnName!} &gt;= <#noparse>#{param.</#noparse>${column.javaField!}Begin}
                    </if>
                    <if test="param.${column.javaField!}End != null">
                        AND ${tableName!}.${column.columnName!} &lt;= <#noparse>#{param.</#noparse>${column.javaField!}End}
                    </if>
                <#else>
                    <if test="param.${column.javaField!} != null">
                        AND ${tableName!}.${column.columnName!} = <#noparse>#{param.</#noparse>${column.javaField!}}
                    </if>
                </#if>
                <#if column.javaType == "String" && column.queryType == "LIKE">
                    <if test="param.${column.javaField!}Like != null and param.${column.javaField!}Like != ''">
                        AND ${tableName!}.${column.columnName!} like concat('%', <#noparse>#{param.</#noparse>${column.javaField!}Like}, '%')
                    </if>
                </#if>
            </#list>
            <if test="authSql != null and authSql != ''">
                ${authSql!}
            </if>
        </trim>
    </sql>

    ## orderFlag = 0 无排序  orderFlag = 1 主键排序  orderFlag = 2 更新时间排序  orderFlag = 3 更新时间、主键排序（只有更新时间 值相同时分页数据排序可能会出现问题）
    <sql id="${ClassName}_Order_Item">
        <#assign orderFlag = 0>
        <#list columns as column>
            <#if column.isPk == '1'>
                <#if orderFlag == 0>
                    <#assign orderFlag = 1>
                <#else>
                    <#assign orderFlag = 3>
                </#if>
            <#elseif column.columnName == "update_time" || column.columnName == "UPDATE_TIME">
                <#if orderFlag == 0>
                    <#assign orderFlag = 2>
                <#else>
                    <#assign orderFlag = 3>
                </#if>
            </#if>
        </#list>
        <#if orderFlag == 1>
            order by
            <#list columns as column>
                <#if column.isPk == '1'>
                    ${tableName!}.${column.columnName!} desc
                </#if>
            </#list>
        <#elseif orderFlag == 2>
            order by
            <#list columns as column>
                <#if column.columnName == "update_time" || column.columnName == "UPDATE_TIME">
                    ${tableName!}.${column.columnName!} desc
                </#if>
            </#list>
        <#elseif orderFlag == 3>
            order by
            <#list columns as column>
                <#if column.columnName == "update_time" || column.columnName == "UPDATE_TIME">
                    ${tableName!}.${column.columnName!} desc,
                </#if>
            </#list>
            <#list columns as column>
                <#if column.isPk == '1'>
                    ${tableName!}.${column.columnName!} desc
                </#if>
            </#list>
        </#if>
    </sql>

    <select id="query${ClassName}List" resultMap="${className}VOMap" parameterType="${packageName}.${moduleName}.model.${ClassName}DTO">
        select
        <include refid="${ClassName}_column" />
        from ${tableName!} ${tableName!}
        <include refid="${ClassName}_Query_List_Sharing" />
        <include refid="${ClassName}_Order_Item" />
    </select>
</mapper>