package com.bmsoft.juc.interrupt;

import java.util.concurrent.TimeUnit;

/**
 * @program: lds-space-master
 * @description: interrupt简单演示
 * @author: 李东升
 * @create: 2019-09-03 16:43
 **/
public class InterruptDemo {

    private static int i;

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {//默认是false  _interrupted state?
                i++;
            }
            System.out.println("i:" + i);
        });
        thread.start();

        TimeUnit.SECONDS.sleep(1);
        thread.interrupt(); //把isInterrupted设置成true
    }
}
