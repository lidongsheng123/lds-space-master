package com.bmsoft.thread.test;

/**
 * @program: lds-space-master
 * @description: 线程测试
 * @author: 李东升
 * @create: 2019-08-21 17:35
 **/
public class ThreadTest {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + ":" + 1);
        });
        t1.start();
        t1.join();


        Thread t2 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + ":" + 2);
        });
        t2.start();
        t2.join();
        Thread t3 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + ":" + 3);
        });
        t3.start();
        t3.join();
    }
}
