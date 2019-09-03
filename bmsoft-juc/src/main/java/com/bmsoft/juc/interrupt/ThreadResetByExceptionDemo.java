package com.bmsoft.juc.interrupt;

import java.util.concurrent.TimeUnit;

/**
 * @program: lds-space-master
 * @description: 通过interruptException进行复位
 * @author: 李东升
 * @create: 2019-09-03 16:49
 **/
public class ThreadResetByExceptionDemo {

    private static int i;

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {//默认是false  _interrupted state?
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    break;
                }
            }
            System.out.println("demo");
        });
        thread.start();
        TimeUnit.SECONDS.sleep(1);
        thread.interrupt(); //把isInterrupted设置成true
        System.out.println(thread.isInterrupted());
    }
}
