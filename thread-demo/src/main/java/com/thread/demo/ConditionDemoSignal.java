package com.thread.demo;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @author ：Mr.Li
 * @date ：Created in 2020/3/30 14:45
 */
public class ConditionDemoSignal implements Runnable {

    private Lock lock;
    private Condition condition;

    public ConditionDemoSignal(Lock lock, Condition condition) {
        this.lock = lock;
        this.condition = condition;
    }

    @Override
    public void run() {
        System.out.println("begin -ConditionDemoSignal");
        try {
            lock.lock();
            condition.signal();
            System.out.println("end - ConditionDemoSignal");
        } finally {
            lock.unlock();
        }
    }
}
