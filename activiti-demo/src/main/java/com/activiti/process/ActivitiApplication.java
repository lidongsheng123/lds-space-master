package com.activiti.process;

import org.activiti.spring.boot.SecurityAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author ：Mr.Li
 * @date ：Created in 2019/11/29 12:40
 */
//去掉重复的包
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class ActivitiApplication {


    public static void main(String[] args) {
        SpringApplication.run(ActivitiApplication.class, args);
    }

}
