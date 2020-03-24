package com.thread.demo;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author ：Mr.Li
 * @date ：Created in 2020/3/24 14:03
 */
public class ReentrantLockDemo {

    static ReentrantLock lock=new ReentrantLock();

    public static void main(String[] args) {
        lock.lock();

        lock.unlock();
    }
}
