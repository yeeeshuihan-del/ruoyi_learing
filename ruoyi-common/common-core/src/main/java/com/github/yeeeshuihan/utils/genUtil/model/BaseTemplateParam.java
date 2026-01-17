package com.github.yeeeshuihan.utils.genUtil.model;

import com.github.yeeeshuihan.utils.genUtil.entity.GenColumn;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author yeeeshuihan
 * @date 2026/1/17 11:36
 * @description ftl模板的渲染参数
 */
@Data
public class BaseTemplateParam {

    String tableName;
    
    String packageName;
    
    String moduleName;
    
    String ClassName;
    
    String className;
    
    String functionName;
    
    List<GenColumn> columns;
    
    final String filePath = "D:\\下载\\chrome下载\\generatedemo";
    
    /**不同的ftl不一样**/
    List<String> importList = new ArrayList<>();
    
    String fileName;
    
    String templatePath;
    
    Map<String,Object> customMap;

    public String getclassName() { //区别于大写的Class字段
        return className;
    }

    public void setclassName(String className) {
        this.className = className;
    }

    public void setClassName(String ClassName) {
        this.ClassName = ClassName;
    }

    public String getClassName() {
        return ClassName;
    }
}

