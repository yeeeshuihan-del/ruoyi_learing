package ${packageName}.${moduleName}.controller;

import java.util.Arrays;
import java.util.List;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import lombok.extern.slf4j.Slf4j;
import org.isbg.common.aspect.annotation.AutoLog;
import org.isbg.common.constant.CommonConstant;
import org.isbg.common.constant.SymbolConstant;
import org.isbg.common.model.PageRequest;
import org.isbg.common.model.Result;
import org.springframework.web.bind.annotation.*;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import ${packageName}.${moduleName}.service.I${ClassName}Service;
import ${packageName}.${moduleName}.entity.${ClassName};
import ${packageName}.${moduleName}.model.${ClassName}DTO;
import ${packageName}.${moduleName}.model.${ClassName}VO;

/**
 * ${functionName}控制层
 *
 * @author ${author}
 * @date ${datetime}
 */
@RestController
@RequestMapping("/${serviceName}/${businessName}")
@Tag(name = "${functionName}管理")
@Slf4j
public class ${ClassName}Controller {

	private static final String MENU_NAME = "${functionName}管理";

	private static final String CONTENT = "${functionName}";

	@Autowired
	private I${ClassName}Service ${className}Service;

	/**
	 * ${functionName}管理-分页列表查询${functionName}
	 *
	 * @param pageReq
	 * @return
	 */
	@RequiresPermissions("${permsCodePrefix}:query")
	@AutoLog(value = MENU_NAME + "-分页列表查询" + CONTENT, operateType = CommonConstant.OPERATE_TYPE_QUERY)
	@Operation(summary = "分页列表查询" + CONTENT, description = MENU_NAME + "-分页列表查询" + CONTENT)
	@PostMapping("/page")
	public Result<Page<${ClassName}VO>> query${ClassName}Page(@RequestBody @Validated PageRequest<${ClassName}DTO> pageReq) {
		Page<${ClassName}DTO> page = new Page();
		BeanUtils.copyProperties(pageReq.getPage(), page);
		Page<${ClassName}VO> result = ${className}Service.query${ClassName}Page(page, pageReq.getQueryObj());
		return Result.ok(result);
	}

	/**
	 * ${functionName}管理-主键查询${functionName}
	 *
	 * @param id
	 * @return
	 */
	@RequiresPermissions("${permsCodePrefix}:query")
	@AutoLog(value = MENU_NAME + "-主键查询" + CONTENT, operateType = CommonConstant.OPERATE_TYPE_QUERY)
	@Operation(summary = "主键查询" + CONTENT, description = MENU_NAME + "-主键查询" + CONTENT)
	@GetMapping("/queryById/{id}")
	public Result<${ClassName}VO> query${ClassName}ById(@PathVariable String id) {
		${ClassName} ${className} = ${className}Service.getById(id);
		if (ObjectUtil.isNull(${className})) {
			return Result.error("未找到对应数据");
		}
			${ClassName}VO ${className}VO = BeanUtil.toBean(${className}, ${ClassName}VO.class);
		return Result.ok(${className}VO);
	}

	/**
	 * ${functionName}管理-新增${functionName}
	 *
	 * @param ${className}VO
	 * @return
	 */
	@RequiresPermissions("${permsCodePrefix}:add")
	@AutoLog(value = MENU_NAME + "-新增" + CONTENT, operateType = CommonConstant.OPERATE_TYPE_ADD)
	@Operation(summary = "新增" + CONTENT, description = MENU_NAME + "-新增" + CONTENT)
	@PostMapping("/add")
	public Result<String> add${ClassName}(@RequestBody @Validated ${ClassName}VO ${className}VO) {
		${ClassName} ${className} = BeanUtil.toBean(${className}VO, ${ClassName}.class);
			${className}Service.save(${className});
		return Result.ok("添加成功");
	}

	/**
	 * ${functionName}管理-修改${functionName}
	 *
	 * @param ${className}VO
	 * @return
	 */
	@RequiresPermissions("${permsCodePrefix}:edit")
	@AutoLog(value = MENU_NAME + "-修改" + CONTENT, operateType = CommonConstant.OPERATE_TYPE_EDIT)
	@Operation(summary = "修改" + CONTENT, description = MENU_NAME + "-修改" + CONTENT)
	@PostMapping("/edit")
	public Result<String> edit${ClassName}(@RequestBody @Validated ${ClassName}VO ${className}VO) {
		if (StrUtil.isEmpty(${className}VO.get${pkColumn.javaField}())) {
			return Result.error("缺失主键参数，更新失败");
		}
		if (${className}Service.getById(${className}VO.get${pkColumn.javaField}()) == null) {
			return Result.error("未找到对应数据，更新失败");
		}
		${ClassName} ${className} = BeanUtil.toBean(${className}VO, ${ClassName}.class);
			${className}Service.updateById(${className});
		return Result.ok("修改成功");
	}

	/**
	 * ${functionName}管理-批量删除${functionName}
	 *
	 * @param ids
	 * @return
	 */
	@RequiresPermissions("${permsCodePrefix}:delete")
	@AutoLog(value = MENU_NAME + "-批量删除" + CONTENT, operateType = CommonConstant.OPERATE_TYPE_DEL)
	@Operation(summary = "批量删除" + CONTENT, description = MENU_NAME + "-批量删除" + CONTENT)
	@PostMapping("/deleteBatch/{ids}")
	public Result<String> delete${ClassName}Batch(@PathVariable String ids) {
		List<String> idsList = Arrays.asList(ids.split(SymbolConstant.COMMA));
		LambdaQueryWrapper<${ClassName}> queryWrapper = new LambdaQueryWrapper<>();
		queryWrapper.in(${ClassName}::get${pkColumn.javaField}, idsList);
		if (${className}Service.count(queryWrapper) != idsList.size()) {
			return Result.error("未找到对应数据，无法删除");
		}
			${className}Service.removeByIds(idsList);
		return Result.ok("删除成功");
	}
}