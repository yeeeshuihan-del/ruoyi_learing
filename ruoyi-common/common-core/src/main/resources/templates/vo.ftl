package ${packageName}.${moduleName}.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import java.io.Serializable;

<#-- 循环导入自定义依赖（替换原Velocity的#foreach） -->
<#list importList as import>
    import ${import};
</#list>

/**
* ${functionName}数据传输对象
*
* @author ${author}
* @date ${datetime}
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Schema(name = "${ClassName}VO对象", description = "${functionName!}数据展示对象")
public class ${ClassName}VO implements Serializable {

private static final long serialVersionUID = 1L;

<#-- 循环生成字段（核心逻辑） -->
<#list columns as column>

    @Schema(description = "${column.columnComment!}")
<#-- Date类型字段：添加日期格式化注解 -->
    <#if column.javaType == "Date">
        @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
        @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    </#if>
    private ${column.javaType} ${column.javaField};

</#list>

}