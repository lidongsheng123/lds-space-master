package com.bmsoft.thread.test;

import java.util.concurrent.TimeUnit;

/**
 * @program: lds-space-master
 * @description: 线程测试
 * @author: 李东升
 * @create: 2019-08-21 17:35
 **/
public class ThreadTest {
    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            try {
                while (true) {
                    TimeUnit.SECONDS.sleep(100);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "Time_Waiting_Thread").start();

        new Thread(() -> {
            while (true) {
                synchronized (ThreadTest.class) {
                    try {
                        ThreadTest.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "Wait_Tread").start();

        new Thread(new BlockedDemo(),"blocked01").start();
        new Thread(new BlockedDemo(),"blocked02").start();
    }

    static class BlockedDemo extends Thread {
        @Override
        public void run() {
            synchronized (BlockedDemo.class) {
                while (true) {
                    try {
                        TimeUnit.SECONDS.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
