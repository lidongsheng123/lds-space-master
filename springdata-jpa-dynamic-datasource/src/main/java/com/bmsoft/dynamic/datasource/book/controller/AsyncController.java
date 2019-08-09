package com.bmsoft.dynamic.datasource.book.controller;

import com.bmsoft.dynamic.datasource.config.AsyncTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: lds-space-master
 * @description: 异步测试
 * @author: 李东升
 * @create: 2019-04-22 14:36
 **/
@RestController
public class AsyncController {

    @Autowired
    private AsyncTask asyncTask;

    @GetMapping("/test/async")
    public String asyncTest() throws InterruptedException {
        long currentTimeMillis = System.currentTimeMillis();
        asyncTask.task();
        long currentTimeMillis1 = System.currentTimeMillis();
        return "task任务总耗时:" + (currentTimeMillis1 - currentTimeMillis) + "ms";
    }
}
