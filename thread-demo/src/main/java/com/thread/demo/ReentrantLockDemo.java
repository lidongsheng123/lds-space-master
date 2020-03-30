package com.thread.demo;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author ：Mr.Li
 * @date ：Created in 2020/3/24 14:03
 */
public class ReentrantLockDemo {

    static ReentrantLock lock = new ReentrantLock();
    private static int i;


    public  static void add() {
        try {
            lock.lock();
            TimeUnit.SECONDS.sleep(1000);
            i++;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        for (int j = 0; j < 100; j++) {
            new Thread(ReentrantLockDemo::add).start();

        }
    }
}
