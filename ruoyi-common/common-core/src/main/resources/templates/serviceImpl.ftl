package ${packageName}.${moduleName}.service.impl;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import ${packageName}.${moduleName}.service.I${ClassName}Service;
import ${packageName}.${moduleName}.mapper.${ClassName}Mapper;
import ${packageName}.${moduleName}.entity.${ClassName};
import ${packageName}.${moduleName}.model.${ClassName}DTO;
import ${packageName}.${moduleName}.model.${ClassName}VO;

/**
* ${functionName}服务接口实现
*
* @author ${author}
* @date ${datetime}
*/
@Slf4j
@Service
public class ${ClassName}ServiceImpl extends ServiceImpl<${ClassName}Mapper, ${ClassName}> implements I${ClassName}Service {

@Autowired
private ${ClassName}Mapper ${className}Mapper;

@Override
public Page<${ClassName}VO> query${ClassName}Page(Page<${ClassName}DTO> page, ${ClassName}DTO ${className}DTO) {
		//String authSql = QueryGenerator.installAuthJdbc(DataPermissionConstant.USER_MODULE_RULE);
		Page<${ClassName}VO> resultPage = ${className}Mapper.query${ClassName}List(page, ${className}DTO, null);
			return resultPage;
		}

		@Override
		public List<${ClassName}VO> query${ClassName}List(${ClassName}DTO ${className}DTO) {
		List<${ClassName}VO> resultList = ${className}Mapper.query${ClassName}List(${className}DTO, null);
			return resultList;
		}
}