package com.thread.demo;

import java.util.concurrent.CountDownLatch;

/**
 * @author ：Mr.Li
 * @date ：Created in 2020/4/7 12:54
 */
public class CountDownLatchDemo {
    private static CountDownLatch count = new CountDownLatch(100);

    public static void main(String[] args) throws InterruptedException {

        for (int i = 0; i <100 ; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName());
            }).start();
            count.countDown();
        }
        count.await();
    }
}
