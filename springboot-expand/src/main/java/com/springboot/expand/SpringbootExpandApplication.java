package com.springboot.expand;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.concurrent.LinkedBlockingDeque;

@SpringBootApplication
public class SpringbootExpandApplication {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(SpringbootExpandApplication.class);
        ConfigurableApplicationContext run = springApplication.run(args);

    }

}
