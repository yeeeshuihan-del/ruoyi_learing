package com.github.yeeeshuihan.utils.genUtil;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author zengxiangbao
 * @date 2026/1/17 12:12
 * @description
 */
@SpringBootTest
public class GenTest {

    @Autowired
    private GenerateService generateService;

    @Test
    public void test() {
        final String tableName = "t_user";
        final String moduleName = "modules.system";
        final String packageName = "com.github.yeeeshuihan";
        generateService.execute(tableName, packageName, moduleName);
    }
}
