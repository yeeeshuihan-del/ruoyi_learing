package ${packageName}.${moduleName}.service;

import java.util.List;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import ${packageName}.${moduleName}.entity.${ClassName};
import ${packageName}.${moduleName}.model.${ClassName}DTO;
import ${packageName}.${moduleName}.model.${ClassName}VO;

/**
* ${functionName}服务接口
*
* @author ${author}
* @date ${datetime}
*/
public interface I${ClassName}Service extends IService<${ClassName}>{

	/**
	* 分页查询
	*
	* @param page
	* @param ${className}DTO
	* @return
	*/
	Page<${ClassName}VO> query${ClassName}Page(Page<${ClassName}DTO> page, ${ClassName}DTO ${className}DTO);

	/**
	* 列表查询
	*
	* @param ${className}DTO
	* @return
	*/
	List<${ClassName}VO> query${ClassName}List(${ClassName}DTO ${className}DTO);

}