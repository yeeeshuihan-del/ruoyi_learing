package ${packageName}.${moduleName}.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import java.io.Serializable;
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
@Schema(name = "${className}DTO对象", description = "${functionName!}数据传输对象")
public class ${ClassName}DTO implements Serializable {

    private static final long serialVersionUID = 1L;
<#list columns as column>

    @Schema(description = "${column.columnComment}")
    <#if column.javaType == "Date">
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private ${column.javaType} ${column.javaField};

    @Schema(description = "${column.columnComment}开始时间")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private ${column.javaType} ${column.javaField}Begin;

    @Schema(description = "${column.columnComment}结束时间")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private ${column.javaType} ${column.javaField}End;
    <#else>
    private ${column.javaType} ${column.javaField};
    </#if>

</#list>
}