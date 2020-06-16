package com.springboot.demo.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author ：Mr.Li
 * @date ：Created in 2020/4/26 15:36
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WebControllerTest {

    @Autowired
    WebController webController;

    @Test
    public void findInfoById() {
        webController.findInfoById();
    }

    @Test
    public void getSourceInfo() {
        webController.getSourceInfo();
    }
}