package com.bmsoft.dynamic.datasource.config;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @program: lds-space-master
 * @description: 异步任务
 * @author: 李东升
 * @create: 2019-04-22 14:37
 **/
@Component
public class AsyncTask {

    @Async
    public void task() throws InterruptedException {
        long currentTimeMillis = System.currentTimeMillis();
        Thread.sleep(5000);
        long currentTimeMillis1 = System.currentTimeMillis();
        System.out.println("task任务耗时:" + (currentTimeMillis1 - currentTimeMillis) + "ms");
    }


}
