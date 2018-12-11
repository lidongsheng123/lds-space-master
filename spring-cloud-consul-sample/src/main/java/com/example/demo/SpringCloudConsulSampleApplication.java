package com.example.demo;

import com.spring4all.swagger.EnableSwagger2Doc;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
@EnableSwagger2Doc
@Api("consul 微服务")
public class SpringCloudConsulSampleApplication {


    @Autowired
    private DiscoveryClient discoveryClient;

    /**
     * 获取所有服务
     */
    @RequestMapping("/services")
    public Object services() {
        return discoveryClient.getServices();
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudConsulSampleApplication.class, args);
    }


    @ApiOperation("测试接口")
    @GetMapping("/user/test")
    public Object test(){

        HashMap<String, Object> map = new HashMap<>();
        map.put("data","123456");
        return map;
    }
}
