package ${packageName}.${moduleName}.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
import ${packageName}.${moduleName}.entity.${ClassName};
import ${packageName}.${moduleName}.model.${ClassName}DTO;
import ${packageName}.${moduleName}.model.${ClassName}VO;

/**
* ${functionName}Mapper接口
*
* @author ${author}
* @date ${datetime}
*/
@Mapper
public interface ${ClassName}Mapper extends BaseMapper<${ClassName}> {
	/**
	* 分页查询
	*
	* @param page
	* @param ${className}DTO
	* @param authSql
	* @return
	*/
	Page<${ClassName}VO> query${ClassName}List(Page<${ClassName}DTO> page, @Param("param") ${ClassName}DTO ${className}DTO, @Param("authSql") String authSql);

	/**
	* 列表查询
	*
	* @param ${className}DTO
	* @param authSql
	* @return
	*/
	List<${ClassName}VO> query${ClassName}List(@Param("param") ${ClassName}DTO ${className}DTO, @Param("authSql") String authSql);

	/**
	* 根据Id物理删除
	*
	* @param id
	*/
	<#list columns as column>
		<#if column.isPk == '1'>
	@Delete("delete from ${tableName!} where ${column.columnName!} = <#noparse>#{id}</#noparse>")
	void delete${ClassName}Physical(@Param("id") String id);
		</#if>
	</#list>
}