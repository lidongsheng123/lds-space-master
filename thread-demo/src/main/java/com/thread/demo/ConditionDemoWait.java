package com.thread.demo;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @author ：Mr.Li
 * @date ：Created in 2020/3/30 14:44
 */
public class ConditionDemoWait implements Runnable {

    private Lock lock;
    private Condition condition;

    public ConditionDemoWait(Lock lock, Condition condition) {
        this.lock = lock;
        this.condition = condition;
    }

    @Override
    public void run() {
        System.out.println("begin -ConditionDemoWait");
        try {
            lock.lock();
            condition.await();
            System.out.println("end - ConditionDemoWait");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
