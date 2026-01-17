package com.github.yeeeshuihan.utils.genUtil;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.builder.CustomFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.github.yeeeshuihan.utils.genUtil.entity.GenTable;
import com.github.yeeeshuihan.utils.genUtil.mapper.GenTableMapper;
import com.github.yeeeshuihan.utils.genUtil.model.BaseTemplateParam;
import com.github.yeeeshuihan.utils.genUtil.model.BasicParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;
/**
 * @author yeeeshuihan
 * @date 2026/1/17 11:13
 * @description
 */
@Service
@Slf4j
public class GenerateService {

    static BasicParam basicParams = new BasicParam();

    private static BaseTemplateParam baseTemplateParam = new BaseTemplateParam();
    
    @Autowired
    private GenTableMapper genTableMapper;
    
    /**
     * 构建参数baseTemplateParam
     * 生成类的全类名  package ${packageName}.${moduleName}.controller;
     * 
     * @param tableName   表名
     * @param packageName 包名
     * @param moduleName  模块名
     */
    public void initContext(String tableName, String packageName, String moduleName) {
        log.info("initContext started: " + "表" + tableName + "，包" + packageName + "，模块" + moduleName);
        final String realTableName = tableName.startsWith("t_") ? tableName.substring(2) : tableName;
        String ClassName = underline2Camel(realTableName);
        baseTemplateParam.setTableName(tableName);
        baseTemplateParam.setPackageName(packageName);
        baseTemplateParam.setModuleName(moduleName);
        baseTemplateParam.setClassName(ClassName);
        baseTemplateParam.setclassName(ClassName.substring(0, 1).toLowerCase(Locale.ROOT) + ClassName.substring(1));
        //查询meta-data
        GenTable genTable = genTableMapper.selectTableByName(realTableName);
        genTable.getColumns().forEach(genColumn -> {genColumn.setJavaField(genColumn.getColumnName().substring(0, 1).toUpperCase(Locale.ROOT) + genColumn.getColumnName().substring(1));});
        if (ObjectUtils.isEmpty(genTable) || CollectionUtil.isEmpty(genTable.getColumns())) {
            throw new RuntimeException("表不存在");
        }
        baseTemplateParam.setColumns(genTable.getColumns());
        String tableComment = genTable.getTableComment();
        if (Optional.ofNullable(tableComment).orElse("").endsWith("表")) {
            tableComment = tableComment.substring(0, tableComment.length() - 1);
        }
        baseTemplateParam.setFunctionName(tableComment);
        //转Map参数
        HashMap<String, Object> customMap = object2Map(baseTemplateParam);
        baseTemplateParam.setCustomMap(customMap);
        assert customMap != null;
        customMap.put("author", "yeeeshuihan");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        customMap.put("datetime", formatter.format(new Date()));
        log.info("initContext end: " + baseTemplateParam.toString());
    }
    
    /**
     * 下划线转大驼峰
     */
    public String underline2Camel(String realTableName) {
        return Arrays.stream(realTableName.split("_")).filter(StrUtil::isNotBlank).map(s -> s.substring(0, 1).toUpperCase(Locale.ROOT) + s.substring(1)).collect(Collectors.joining(""));
    }

//    public static String underlineTOCamel2(String realTableName, boolean allUpper) {
//        assert !realTableName.isEmpty() && realTableName.charAt(0) != '_';
//        StringBuilder res = new StringBuilder();
//        boolean upper = allUpper;
//        for (int i = 0; i < realTableName.length(); i++) {
//            char c = realTableName.charAt(i);
//            if (c == '_') {
//                upper = true;
//            } else {
//                if (upper) {
//                    res.append(Character.toUpperCase(c));
//                    upper = false;
//                } else {
//                    res.append(c);
//                }
//            }
//        }
//        return res.toString();
//    }
    
    /**
     * 获取不同ftl的特定参数
     * 
     * @param  templateName      ftl名称
     * @return BaseTemplateParam 模板对应的完整参数
     */
    public BaseTemplateParam getTemplateParam(String templateName) {
//        String classname = baseTemplateParam.getClassName();
        String suffix = "";
        Map<String, Object> customMap = baseTemplateParam.getCustomMap();
        switch (templateName) {
            case "mapper":
                suffix = "Mapper.java";
                break;
            case "service":
                suffix = "Service.java";
                break;
            case "serviceImpl":
                suffix = "ServiceImpl.java";
                break;
            case "controller":
                suffix = "Controller.java";
                customMap.put("businessName", getBusinessName(baseTemplateParam.getTableName())); //表名
                customMap.put("serviceName", baseTemplateParam.getModuleName()); //模块名（微服务）
                customMap.put("permsCodePrefix", baseTemplateParam.getModuleName() + ":" + getBusinessName(baseTemplateParam.getTableName()));
                customMap.put("pkColumn", baseTemplateParam.getColumns().stream().filter(genColumn -> "1".equals(genColumn.getIsPk())).findFirst().get());
                break;
            case "mapper.xml":
                suffix = "Mapper.xml";
                break;
            case "vo":
                suffix = "VO.java";
                break;
            case "dto":
                suffix = "DTO.java";
                break;
            default:
                suffix = ".java";
                break;
        }
        BaseTemplateParam newBaseTemplateParam = BeanUtil.copyProperties(baseTemplateParam, BaseTemplateParam.class);
        newBaseTemplateParam.setFileName(suffix);
        newBaseTemplateParam.setTemplatePath("/templates/" + templateName + ".ftl");
        return newBaseTemplateParam;
    }
    
    /**
     * 获取业务名
     * 
     * @param tableName 表名
     * @return 业务名
     */
    public static String getBusinessName(String tableName) {
        int lastIndex = tableName.lastIndexOf("_");
        int nameLength = tableName.length();
        return tableName.substring(lastIndex + 1, nameLength);
    }
    
    /**
     * 对象转Map
     * 
     * @param  obj 对象
     * @return Map
     */
    public HashMap<String, Object> object2Map(Object obj) {
        if (ObjectUtil.isEmpty(obj)) return null;
        Field[] fields = obj.getClass().getDeclaredFields();
        HashMap<String, Object> map = new HashMap<>();
        for (Field field : fields) {
            field.setAccessible(true);
            Object value = null;
            try {
                value = field.get(obj);
            } catch (IllegalAccessException e) {
                log.info("获取属性值异常, {}", field.getName());
            }
            map.put(field.getName(), value);
        }
        return map;
    }

    /**
     * 执行生成
     *
     * @param  tableName    表名
     * @param  packageName  包名
     * @param  moduleName   模块名
     */
    public void execute(String tableName, String packageName, String moduleName) {
        initContext(tableName, packageName, moduleName);
        FastAutoGenerator.create(basicParams.getUrl(), basicParams.getUsername(), basicParams.getPassword())
                .injectionConfig(getInjectionConsumer("dto"))
                .injectionConfig(getInjectionConsumer("entity"))
                .injectionConfig(getInjectionConsumer("mapper"))
                .injectionConfig(getInjectionConsumer("service"))
                .injectionConfig(getInjectionConsumer("controller"))
                .injectionConfig(getInjectionConsumer("mapper.xml"))
                .injectionConfig(getInjectionConsumer("serviceImpl"))
                .injectionConfig(getInjectionConsumer("vo"))
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();
    }

    /**
     * 获取注入参数
     *
     * @param  templateName ftl名称
     * @return Consumer     injection参数
     */
    public Consumer<InjectionConfig.Builder> getInjectionConsumer(String templateName) {
        BaseTemplateParam templateParam = getTemplateParam(templateName);
        return injectConfig -> {
//            injectConfig.beforeOutputFile((tableInfo, map) -> {
//                tableInfo.set
//            });
            injectConfig.customMap(templateParam.getCustomMap()); //注入自定义属性
            injectConfig.customFile(new CustomFile.Builder()
                            .enableFileOverride()
                            .filePath(templateParam.getFilePath())
                            .fileName(templateParam.getFileName()) //文件名称
                            .formatNameFunction(tableInfo -> tableInfo.getServiceName().endsWith("Service") ? "I" + tableInfo.getServiceName() : tableInfo.getServiceName())
                            .templatePath(templateParam.getTemplatePath())
                            .build()//指定生成模板路径
            );
        };
    }
}
