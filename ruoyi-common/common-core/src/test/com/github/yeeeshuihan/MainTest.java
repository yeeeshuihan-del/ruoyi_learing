package com.github.yeeeshuihan;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.IFill;
import com.baomidou.mybatisplus.generator.config.builder.CustomFile;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.fill.Column;
import com.baomidou.mybatisplus.generator.function.ConverterFileName;
import com.baomidou.mybatisplus.generator.model.ClassAnnotationAttributes;
import com.github.yeeeshuihan.entity.GenColumn;
import com.github.yeeeshuihan.entity.GenTable;
import com.github.yeeeshuihan.mapper.GenTableMapper;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.ObjectUtils;

import java.io.File;
import java.lang.reflect.Field;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;
//import java.util.logging.SimpleFormatter;
import java.util.stream.Collectors;

import static com.baomidou.mybatisplus.generator.config.ConstVal.*;

/**
 * @author zengxiangbao
 * @date 2026/1/3 14:26
 * @description
 */
@SpringBootTest(classes = CommonCoreApplication.class)
@Slf4j
public class MainTest {

    @Autowired
    private GenTableMapper genTableMapper;

    @Test
    public void contextLoads() {
    }

    @Test
    public void test() {
        final String url ="jdbc:mysql://localhost/learing-ruoyi?characterEncoding=UTF-8&useUnicode=true&useSSL=false&tinyInt1isBit=false&allowPublicKeyRetrieval=true&serverTimezone=Asia/Shanghai&rewriteBatchedStatements=true&connectTimeout=300000&socketTimeout=300000";
        GenerateConfig config = new GenerateConfig();
        //参数表名，包名， 模块名
        String tableName = "t_sys_user";
        String moduleName = "generatedemo";
        String packageName = "com.github.yeeeshuihan";

//        String tableName;
//        String packageName;
//        String moduleName;
//        List<String> importList;
//        String className;
//        String functionName;
//        List<GenColumn> columnList;
        final String realTableName = tableName.startsWith("t_") ? tableName.substring(2) : tableName;
        String className = Arrays.stream(realTableName.split("_")).filter(StrUtil::isNotBlank).map(s -> s.substring(0, 1).toUpperCase(Locale.ROOT) + s.substring(1)).collect(Collectors.joining(""));

        GenerateConfig.TableInfo tableInfo = GenerateConfig.TableInfo.builder()
                .tableName(tableName)
                .packageName(packageName)
                .moduleName(moduleName)
                .importList(Arrays.asList())
                .className(className)
                .build();
        //查询meta-data
        GenTable genTable = genTableMapper.selectTableByName(realTableName);
        if (ObjectUtils.isEmpty(genTable) || CollectionUtil.isEmpty(genTable.getColumnList())) {
            throw new RuntimeException("表不存在");
        }
        tableInfo.setColumnList(genTable.getColumnList());
        String tableComment = genTable.getTableComment();
        if (Optional.ofNullable(tableComment).orElse("").endsWith("表")) {
            tableComment = tableComment.substring(0, tableComment.length() - 1);
        }
        tableInfo.setFunctionName(tableComment);
        //转Map参数
        Map<String,Object> customMap = object2Map(tableInfo);
        customMap.put("author", "zengxiangbao");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        customMap.put("datetime", formatter.format(new Date()));

        FastAutoGenerator.create(url, "root", "123456")
                .globalConfig(builder -> builder
                        .author("Baomidou")
                        .outputDir("D:\\下载\\chrome下载" + File.separator +"gen")
                        .commentDate("yyyy-MM-dd")
                )
                .packageConfig(builder -> builder
                        .parent("com.baomidou.mybatisplus")
                        .entity("entity")
                        .mapper("mapper")
                        .service("service")
                        .serviceImpl("service.impl")
                        .xml("mapper.xml")
                )
                .strategyConfig(builder -> builder
                        .addInclude("sys_user")
                        .entityBuilder()
                        .enableLombok()
                        .enableFileOverride()
                        .controllerBuilder()
                        .enableRestStyle() //生成@RestController
                        .enableFileOverride()
                        .mapperBuilder()
                        .enableFileOverride()
                        .serviceBuilder()
                        .enableFileOverride()

                )
                .injectionConfig(injectConfig -> { //dto
                    injectConfig.customMap(customMap); //注入自定义属性
                    injectConfig.customFile(new CustomFile.Builder()
                            .enableFileOverride()
                            .filePath("D:\\下载\\chrome下载")
                            .fileName("entityDTO.java") //文件名称
                            .templatePath("/templates/dto.ftl") //指定生成模板路径
                            .packageName("dto") //包名,自3.5.10开始,可通过在package里面获取自定义包全路径,低版本下无法获取,示例:package.entityDTO
                            .build());
                })
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();
    }

    public Map<String, Object> object2Map(Object obj) {
        if (ObjectUtil.isEmpty(obj)) return null;
        Field[] fields = obj.getClass().getDeclaredFields();
        Map<String, Object> map = new HashMap<>();
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

    @Test
    public void test2() {
        GenTable res = genTableMapper.selectTableByName("sys_user");
        System.out.println(object2Map(res));
    }
}

class GenerateConfig {

    BasicParam basicParams;

    //固定值
    public static class BasicParam {
        final String url = "jdbc:mysql://localhost/learing-ruoyi?characterEncoding=UTF-8&useUnicode=true&useSSL=false&tinyInt1isBit=false&allowPublicKeyRetrieval=true&serverTimezone=Asia/Shanghai&rewriteBatchedStatements=true&connectTimeout=300000&socketTimeout=300000";
        final String username = "root";
        final String password = "123456";
        final String outputDir = "D:\\下载\\chrome下载" + File.separator +"";
        final String author = "zengxiangbao";
        final String commentDate = "yyyy-MM-dd";
        final boolean disableOpenDir = false;
        final String parent = "com.github.yeeeshuihan";
    }

    //参数
    @Data
    @Builder
    public static class TableInfo {
        String tableName;
        String packageName;
        String moduleName;
        List<String> importList;
        String className;
        String functionName;
        List<GenColumn> columnList;
    }

    public static class EntityStrategyParam {
//        nameConvert(INameConvert)	名称转换实现
//        Class superClass;	//设置父类	BaseEntity.class
//        superClass(String)	设置父类	com.baomidou.global.BaseEntity
        boolean disableSerialVersionUID = false;
        boolean enableFileOverride = false;
//        enableColumnConstant	开启生成字段常量	默认值: false
//        enableChainModel	开启链式模型	默认值: false
//        enableLombok	开启 Lombok 模型	默认值: false 默认只有Getter,Setter,自3.5.10后增加ToString
//        enableRemoveIsPrefix	开启 Boolean 类型字段移除 is 前缀	默认值: false
        boolean enableTableFieldAnnotation =  true;
//        enableActiveRecord	开启 ActiveRecord 模型	默认值: false
//        versionColumnName(String)	乐观锁字段名(数据库字段)	versionColumnName 与 versionPropertyName 二选一即可
//        versionPropertyName(String)	乐观锁属性名(实体)	versionColumnName 与 versionPropertyName 二选一即可
        String logicDeleteColumnName = "valid_tag";
//        logicDeletePropertyName(String)	逻辑删除属性名(实体)	logicDeleteColumnName 与 logicDeletePropertyName 二选一即可
        NamingStrategy naming = NamingStrategy.underline_to_camel;
        NamingStrategy columnNaming = NamingStrategy.underline_to_camel;
//        addTableFills(IFill…)	添加表字段填充

        static {
            Column createTime = new Column("create_time", FieldFill.INSERT);
            Column updateTime = new Column("update_time", FieldFill.UPDATE);
            Column createUser = new Column("delete_time", FieldFill.INSERT);
            Column updateUser = new Column("update_time", FieldFill.UPDATE);
            addTableFills = List.of(createTime, updateTime, createUser, updateUser);
        }
        static List<IFill> addTableFills; //添加表字段填充
        IdType idType = IdType.ASSIGN_ID;
//        ConverterFileName fileNameConverter = (fileType, entityName) -> {
//            return switch (fileType) {
//                case ENTITY -> entityName + "DO"; // Entity → SysUserDO
//                case MAPPER -> entityName + "Dao"; // Mapper → SysUserDao
//                case SERVICE -> entityName + "Service"; // Service → SysUserService
//                case SERVICE_IMPL -> entityName + "ServiceImpl"; // ServiceImpl → SysUserServiceImpl
//                case CONTROLLER -> entityName + "Controller"; // Controller → SysUserController
//                case XML -> entityName + "Dao"; // XML → SysUserDao.xml
//                default -> entityName; // 其他文件沿用默认名称
//            }
//        };//转换文件名称

         //String formatFileName = "%sEntity";	//格式化文件名称

//        toString(boolean)	是否生成ToString方法	默认为true, 自3.5.10开始
//        fieldUseJavaDoc	启用字段文档注释	默认为true, 自3.5.10开始

//        static {
//            ClassAnnotationAttributes apiModelAnnotation = ClassAnnotationAttributes.builder("ApiModel")
//                    .attr("value", "SysUserEntity") // 设置注解的 value 属性
//                    .attr("description", "用户实体类") // 设置注解的 description 属性
//                    .importPackages("io.swagger.annotations.ApiModel") // 指定包路径
//                    .build();
//        }
//        ClassAnnotationAttributes classAnnotations = new ClassAnnotationAttributes("@TableName(\"%s\")");	//添加实体类注解	自3.5.10开始

//        tableAnnotationHandler	表注解处理器	自3.5.10开始
//        tableFieldAnnotationHandler	字段注解处理器	自3.5.10开始

        //添加实体类@Data注解
        static {
            enableLombok = new ClassAnnotationAttributes("@Data","lombok.Data");
        }
        static ClassAnnotationAttributes enableLombok;	//开启 Lombok 模型并设置Lombok注解	自3.5.10开始. 使用@Data示例: enableLombok(new ClassAnnotationAttributes(“@Data”,“lombok.Data”))
    }
}
