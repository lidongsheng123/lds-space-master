package com.bmsoft.juc;

import java.util.concurrent.TimeUnit;

/**
 * @program: lds-space-master
 * @description: 线程测试
 * @author: 李东升
 * @create: 2019-09-03 14:35
 **/
public class ThreadDemo {

    private static int i;

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println("demo");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    break;
                }
                System.out.println(i += 1);
            }
        });

        t1.start();
      /*  TimeUnit.SECONDS.sleep(100);*/
        t1.interrupt();

        System.out.println(t1.isInterrupted());
    }
}
