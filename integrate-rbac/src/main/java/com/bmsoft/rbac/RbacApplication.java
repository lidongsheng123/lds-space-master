package com.bmsoft.rbac;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author 李东升
 * @version 1.0.0
 * @ClassName RbacApplication.java
 * @Description TODO
 * @createTime 2019年01月10日 18:30:00
 **/
@SpringBootApplication
@EntityScan("com.bmsoft.rbac.entity")
@ComponentScan("com.bmsoft.rbac")
public class RbacApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(RbacApplication.class)
                .build(args)
                .run();
    }
}
