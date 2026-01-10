package com.github.yeeeshuihan.common.core.utils;

import cn.hutool.core.util.ObjectUtil;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zengxiangbao
 * @date 2026/1/4 9:05
 * @description
 */
@Slf4j
public class GenUtil {

    public static HashMap<String, Object> object2Map(Object obj) {
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
}
