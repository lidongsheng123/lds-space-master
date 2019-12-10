package com.poi.demo.poi;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootTest
class PoiDemoApplicationTests {


    @Test
    void contextLoads() {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        executorService.submit(()->{
        });
    }

}
