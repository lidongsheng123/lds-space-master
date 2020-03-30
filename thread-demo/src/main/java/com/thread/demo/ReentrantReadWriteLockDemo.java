package com.thread.demo;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author ：Mr.Li
 * @date ：Created in 2020/3/28 9:29
 */
public class ReentrantReadWriteLockDemo {

    static Map<String, Object> cacheMap = new HashMap<>();

    static ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
    static Lock read = rwl.readLock();
    static Lock write = rwl.writeLock();

    public static final Object get(String key) {
        System.out.println(" 开始读取数据");
        read.lock(); // 读锁
        try {
            return cacheMap.get(key);
        } finally {
            read.unlock();
        }
    }

    public static final Object put(String key,Object value){
        write.lock();
        System.out.println(" 开始写数据");
        try{
            return cacheMap.put(key,value);
        }finally {
            write.unlock();
        }
    }


}
