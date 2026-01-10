package ${packageName}.${moduleName}.entity;

<#-- 循环导入自定义依赖 -->
<#list importList as import>
    import ${import};
</#list>
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
* ${functionName}数据对象
*
* @author ${author}
* @date ${datetime}
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("${tableName}")
@Schema(name = "${ClassName}对象", description = "${functionName}数据对象")
public class ${ClassName} implements Serializable {

    private static final long serialVersionUID = 1L;

<#-- 循环生成字段 -->
<#list columns as column>
    @Schema(description = "${column.columnComment!}")
    <#if column.isPk == '1'>
    @TableId(value = "${column.columnName}", type = IdType.ASSIGN_ID)
    <#else>
    @TableField("${column.columnName}")
    </#if>
    <#if column.javaType == 'Date'>
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    </#if>
    private ${column.javaType} ${column.javaField};

</#list>
}