package com.bmsoft.dynamic.datasource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @program: lds-space-master
 * @description: 启动类
 * @author: 李东升
 * @create: 2019-04-04 11:19
 **/
@SpringBootApplication
@EnableAsync
public class DynamicApplication {

    public static void main(String[] args) {
        SpringApplication.run(DynamicApplication.class, args);
    }
}
