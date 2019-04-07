package com.bmsoft.dynamic.datasource;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @program: lds-space-master
 * @description: 初始化servelt
 * @author: 李东升
 * @create: 2019-04-04 12:03
 **/

public class ServletInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(DynamicApplication.class);
    }
}
