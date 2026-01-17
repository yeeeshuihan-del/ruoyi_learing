//package com.github.yeeeshuihan.common;
//
//import cn.hutool.core.bean.BeanUtil;
//import cn.hutool.core.collection.CollectionUtil;
//import cn.hutool.core.util.StrUtil;
//import cn.hutool.extra.spring.SpringUtil;
//import com.baomidou.mybatisplus.generator.FastAutoGenerator;
//import com.baomidou.mybatisplus.generator.config.InjectionConfig;
//import com.baomidou.mybatisplus.generator.config.builder.CustomFile;
//import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
//import com.github.yeeeshuihan.utils.genUtil.entity.GenColumn;
//import com.github.yeeeshuihan.utils.genUtil.entity.GenTable;
//import com.github.yeeeshuihan.utils.genUtil.mapper.GenTableMapper;
//import com.github.yeeeshuihan.utils.genUtil.GenUtil;
//import lombok.Data;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.util.ObjectUtils;
//
//import java.io.File;
//import java.text.SimpleDateFormat;
//import java.util.*;
////import java.util.logging.SimpleFormatter;
//import java.util.function.Consumer;
//import java.util.stream.Collectors;
//
///**
// * @author yeeeshuihan
// * @date 2026/1/3 14:26
// * @description
// */
//@SpringBootTest
//@Slf4j
//public class MainTest {
//
//    @Autowired
//    private GenTableMapper genTableMapper;
//
//    @Test
//    public void contextLoads() {
//    }
//
//    @Test
//    public void test() {
//        final String url ="jdbc:mysql://localhost/learing-ruoyi?characterEncoding=UTF-8&useUnicode=true&useSSL=false&tinyInt1isBit=false&allowPublicKeyRetrieval=true&serverTimezone=Asia/Shanghai&rewriteBatchedStatements=true&connectTimeout=300000&socketTimeout=300000";
//        //参数表名，包名， 模块名
//        String tableName = "sys_user";
//        String moduleName = "generatedemo";
//        String packageName = "com.github.yeeeshuihan";
//
//        GenerateConfigFactory.initContext(tableName, packageName, moduleName);
//        GenerateConfigFactory.BasicParam basicParam = GenerateConfigFactory.getBasicParams();
////        String tableName;
////        String packageName;
////        String moduleName;
////        List<String> importList;
////        String className;
////        String functionName;
////        List<GenColumn> columnList;
//        
//
//        FastAutoGenerator.create(url, basicParam.username, basicParam.password)
////                .globalConfig(builder -> builder
////                        .author(basicParam.author)
////                        .outputDir(basicParam.outputDir)
////                        .commentDate(basicParam.commentDate)
////                )
////                .packageConfig(builder -> builder
////                        .parent(basicParam.parent)
////                        .entity("entity")
////                        .mapper("mapper")
////                        .service("service")
////                        .serviceImpl("service.impl")
////                        .xml("mapper.xml")
////                )
////                .strategyConfig(builder -> builder
////                        .addInclude("sys_user")
////                        .entityBuilder()
////                        .enableLombok()
////                        .enableFileOverride()
////                        .controllerBuilder()
////                        .enableRestStyle() //生成@RestController
////                        .enableFileOverride()
////                        .mapperBuilder()
////                        .enableFileOverride()
////                        .serviceBuilder()
////                        .enableFileOverride()
//
////                )
//                .injectionConfig(getInjectionConsumer("dto"))
//                .injectionConfig(getInjectionConsumer("entity"))
//                .injectionConfig(getInjectionConsumer("mapper"))
//                .injectionConfig(getInjectionConsumer("service"))
//                .injectionConfig(getInjectionConsumer("controller"))
//                .injectionConfig(getInjectionConsumer("mapper.xml"))
//                .injectionConfig(getInjectionConsumer("serviceImpl"))
//                .injectionConfig(getInjectionConsumer("vo"))
//                .templateEngine(new FreemarkerTemplateEngine())
//                .execute();
//
//    }
//
//    public Consumer<InjectionConfig.Builder> getInjectionConsumer(String templateName) {
//        GenerateConfigFactory.BaseTemplateParam param = GenerateConfigFactory.getTemplateParam(templateName);
//        Consumer<InjectionConfig.Builder> consumer = injectConfig -> { //dto
////            injectConfig.beforeOutputFile((tableInfo, map) -> {
////                tableInfo.set
////            });
//            injectConfig.customMap(param.customMap); //注入自定义属性
//            injectConfig.customFile(new CustomFile.Builder()
//                    .enableFileOverride()
//                    .filePath(param.filePath)
//                    .fileName(param.fileName) //文件名称
//                    .formatNameFunction(tableInfo -> tableInfo.getServiceName().endsWith("Service") ? "I" + tableInfo.getServiceName() : tableInfo.getServiceName())
//                    .templatePath(param.templatePath)
//                    .build()//指定生成模板路径
////                    .packageName(param.packageName) //包名,自3.5.10开始,可通过在package里面获取自定义包全路径,低版本下无法获取,示例:package.entityDT
//            );
//        };
//        return consumer;
//    }
//}
//
//@Data
//class GenerateConfigFactory {
//
//    static BasicParam basicParams;
//    //固定值
//    @Data
//    public static class BasicParam {
//        final String url = "jdbc:mysql://localhost/learing-ruoyi?characterEncoding=UTF-8&useUnicode=true&useSSL=false&tinyInt1isBit=false&allowPublicKeyRetrieval=true&serverTimezone=Asia/Shanghai&rewriteBatchedStatements=true&connectTimeout=300000&socketTimeout=300000";
//        final String username = "root";
//        final String password = "123456";
//        final String outputDir = "D:\\下载\\chrome下载" + File.separator +"";
//        final String author = "yeeeshuihan";
//        final String commentDate = "yyyy-MM-dd";
//        final boolean disableOpenDir = false;
//        final String parent = "com.github.yeeeshuihan";
//    }
//
//    public static BasicParam getBasicParams() {
//        if (ObjectUtils.isEmpty(basicParams)) {
//            basicParams = new BasicParam();
//        }
//        return basicParams;
//    }
//
//    //模板通用参数
//    @Data
//    public static class BaseTemplateParam {
//        String tableName;
//        String packageName;
//        String moduleName;
//        String ClassName;
//        String className;
//        String functionName;
//        List<GenColumn> columns;
//        final String filePath = "D:\\下载\\chrome下载\\generatedemo";
//        //不同的ftl不一样
//        List<String> importList = new ArrayList<>();
//        String fileName;
//        String templatePath;
//        Map<String,Object> customMap;
//
//        public String getclassName() {
//            return className;
//        }
//
//        public void setclassName(String className) {
//            this.className = className;
//        }
//        public String getClassName() {
//            return ClassName;
//        }
//
//        public void setClassName(String className) {
//            this.ClassName = className;
//        }
//    }
//    
//    private static BaseTemplateParam baseTemplateParam = new BaseTemplateParam();
//    
////    public static class EntityStrategyParam {
//////        nameConvert(INameConvert)	名称转换实现
//////        Class superClass;	//设置父类	BaseEntity.class
//////        superClass(String)	设置父类	com.baomidou.global.BaseEntity
////        boolean disableSerialVersionUID = false;
////        boolean enableFileOverride = false;
//////        enableColumnConstant	开启生成字段常量	默认值: false
//////        enableChainModel	开启链式模型	默认值: false
//////        enableLombok	开启 Lombok 模型	默认值: false 默认只有Getter,Setter,自3.5.10后增加ToString
//////        enableRemoveIsPrefix	开启 Boolean 类型字段移除 is 前缀	默认值: false
////        boolean enableTableFieldAnnotation =  true;
//////        enableActiveRecord	开启 ActiveRecord 模型	默认值: false
//////        versionColumnName(String)	乐观锁字段名(数据库字段)	versionColumnName 与 versionPropertyName 二选一即可
//////        versionPropertyName(String)	乐观锁属性名(实体)	versionColumnName 与 versionPropertyName 二选一即可
////        String logicDeleteColumnName = "valid_tag";
//////        logicDeletePropertyName(String)	逻辑删除属性名(实体)	logicDeleteColumnName 与 logicDeletePropertyName 二选一即可
////        NamingStrategy naming = NamingStrategy.underline_to_camel;
////        NamingStrategy columnNaming = NamingStrategy.underline_to_camel;
//////        addTableFills(IFill…)	添加表字段填充
////
////        static {
////            Column createTime = new Column("create_time", FieldFill.INSERT);
////            Column updateTime = new Column("update_time", FieldFill.UPDATE);
////            Column createUser = new Column("delete_time", FieldFill.INSERT);
////            Column updateUser = new Column("update_time", FieldFill.UPDATE);
////            addTableFills = List.of(createTime, updateTime, createUser, updateUser);
////        }
////        static List<IFill> addTableFills; //添加表字段填充
////        IdType idType = IdType.ASSIGN_ID;
//////        ConverterFileName fileNameConverter = (fileType, entityName) -> {
//////            return switch (fileType) {
//////                case ENTITY -> entityName + "DO"; // Entity → SysUserDO
//////                case MAPPER -> entityName + "Dao"; // Mapper → SysUserDao
//////                case SERVICE -> entityName + "Service"; // Service → SysUserService
//////                case SERVICE_IMPL -> entityName + "ServiceImpl"; // ServiceImpl → SysUserServiceImpl
//////                case CONTROLLER -> entityName + "Controller"; // Controller → SysUserController
//////                case XML -> entityName + "Dao"; // XML → SysUserDao.xml
//////                default -> entityName; // 其他文件沿用默认名称
//////            }
//////        };//转换文件名称
////
////         //String formatFileName = "%sEntity";	//格式化文件名称
////
//////        toString(boolean)	是否生成ToString方法	默认为true, 自3.5.10开始
//////        fieldUseJavaDoc	启用字段文档注释	默认为true, 自3.5.10开始
////
//////        static {
//////            ClassAnnotationAttributes apiModelAnnotation = ClassAnnotationAttributes.builder("ApiModel")
//////                    .attr("value", "SysUserEntity") // 设置注解的 value 属性
//////                    .attr("description", "用户实体类") // 设置注解的 description 属性
//////                    .importPackages("io.swagger.annotations.ApiModel") // 指定包路径
//////                    .build();
//////        }
//////        ClassAnnotationAttributes classAnnotations = new ClassAnnotationAttributes("@TableName(\"%s\")");	//添加实体类注解	自3.5.10开始
////
//////        tableAnnotationHandler	表注解处理器	自3.5.10开始
//////        tableFieldAnnotationHandler	字段注解处理器	自3.5.10开始
////
////        //添加实体类@Data注解
////        static {
////            enableLombok = new ClassAnnotationAttributes("@Data","lombok.Data");
////        }
////        static ClassAnnotationAttributes enableLombok;	//开启 Lombok 模型并设置Lombok注解	自3.5.10开始. 使用@Data示例: enableLombok(new ClassAnnotationAttributes(“@Data”,“lombok.Data”))
////    }
////
////    public EntityStrategyParam entityStrategyParam;
//
//    private static GenTableMapper genTableMapper = SpringUtil.getBean(GenTableMapper.class);
//    
//    public static void initContext(String tableName, String packageName, String moduleName) {
//        final String realTableName = tableName.startsWith("t_") ? tableName.substring(2) : tableName;
//        String ClassName = underline2Camel(realTableName);
//        baseTemplateParam.setTableName(realTableName);
//        baseTemplateParam.setPackageName(packageName);
//        baseTemplateParam.setModuleName(moduleName);
//        baseTemplateParam.setClassName(ClassName);
//        baseTemplateParam.setclassName(ClassName.substring(0, 1).toLowerCase(Locale.ROOT) + ClassName.substring(1));
//        //查询meta-data
//        GenTable genTable = genTableMapper.selectTableByName(realTableName);
//        genTable.getColumns().forEach(genColumn -> {genColumn.setJavaField(genColumn.getColumnName().substring(0, 1).toUpperCase(Locale.ROOT) + genColumn.getColumnName().substring(1));});
//        if (ObjectUtils.isEmpty(genTable) || CollectionUtil.isEmpty(genTable.getColumns())) {
//            throw new RuntimeException("表不存在");
//        }
//        baseTemplateParam.setColumns(genTable.getColumns());
//        String tableComment = genTable.getTableComment();
//        if (Optional.ofNullable(tableComment).orElse("").endsWith("表")) {
//            tableComment = tableComment.substring(0, tableComment.length() - 1);
//        }
//        baseTemplateParam.setFunctionName(tableComment);
//        //转Map参数
//        HashMap<String, Object> customMap = GenUtil.object2Map(baseTemplateParam);
//        baseTemplateParam.customMap = customMap;
//        assert customMap != null;
//        customMap.put("author", "yeeeshuihan");
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        customMap.put("datetime", formatter.format(new Date()));
//    }
//
////    public GenerateConfig fillTemplateParam(BaseTemplateParam baseTemplateParam, String templateName) {
//////        String fileName =  "entityDTO.java";
//////        final String templatePath = "/templates/dto.ftl";
//////        Map<String,Object> customMap;
////        String templatePath = "template/" + templateName + ".ftl";
////
////        List<String> importList = new ArrayList<>();
////        String basePackage = baseTemplateParam.getPackageName();
////        switch (templateName) {
////            case "entity":
////                baseTemplateParam.setEntityStrategyParam(this.getEntityStrategyParam());
////                break;
////            case "mapper":
////                importList.add("DTO");
////                break;
////            case "service":
////                baseTemplateParam.setServiceStrategyParam()
////        }
////    }
//    public static String underline2Camel(String realTableName) {
//        return Arrays.stream(realTableName.split("_")).filter(StrUtil::isNotBlank).map(s -> s.substring(0, 1).toUpperCase(Locale.ROOT) + s.substring(1)).collect(Collectors.joining(""));
//    }
//
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
//
//    public static BaseTemplateParam getTemplateParam(String templateName) {
//        String classname = baseTemplateParam.getClassName();
//        String suffix = "";
//        Map<String, Object> customMap = baseTemplateParam.customMap;
//        switch (templateName) {
//            case "mapper":
//                suffix = "Mapper.java";
//                break;
//            case "service":
//                suffix = "Service.java";
//                break;
//            case "serviceImpl":
//                suffix = "ServiceImpl.java";
//                break;
//            case "controller":
//                suffix = "Controller.java";
//                customMap.put("businessName", getBusinessName(baseTemplateParam.getTableName())); //表名
//                customMap.put("serviceName", baseTemplateParam.getModuleName()); //模块名（微服务）
//                customMap.put("permsCodePrefix", baseTemplateParam.getModuleName() + ":" + getBusinessName(baseTemplateParam.getTableName()));
//                customMap.put("pkColumn", baseTemplateParam.getColumns().stream().filter(genColumn -> "1".equals(genColumn.getIsPk())).findFirst().get());
//                break;
//            case "mapper.xml":
//                suffix = "Mapper.xml";
//                break;
//            case "vo":
//                suffix = "VO.java";
//                break;
//            case "dto":
//                suffix = "DTO.java";
//                break;
//            default:
//                suffix = ".java";
//                break;
//        }
//        BaseTemplateParam newBaseTemplateParam = BeanUtil.copyProperties(baseTemplateParam, BaseTemplateParam.class);
//        newBaseTemplateParam.fileName = suffix;
//        newBaseTemplateParam.templatePath = "/templates/" + templateName + ".ftl";
//        return newBaseTemplateParam;
//    }
//
//    public static String getBusinessName(String tableName) {
//        int lastIndex = tableName.lastIndexOf("_");
//        int nameLength = tableName.length();
//        return tableName.substring(lastIndex + 1, nameLength);
//    }
//}
