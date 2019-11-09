package com.bmsoft.distributed.lock.zookeeper;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessLock;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.concurrent.CountDownLatch;

/**
 * @program: lds-space-master
 * @description: curator客户端
 * @author: 李东升
 * @create: 2019-04-10 10:30
 **/
public class CuratorDistributedLock {

    public static void main(String[] args) throws Exception {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework curatorFramework = CuratorFrameworkFactory.builder()
                .connectString("127.0.0.1:2181")
                .sessionTimeoutMs(2000)
                .retryPolicy(retryPolicy)
                .build();
        curatorFramework.start();
        InterProcessMutex interProcessMutex = new InterProcessMutex(curatorFramework, "/locks");

        CountDownLatch countDownLatch = new CountDownLatch(10);
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    countDownLatch.await();
                    process(interProcessMutex);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, "Thread-" + i).start();
            countDownLatch.countDown();
        }
    }

    private static void process(InterProcessLock lock) {
        try {
            lock.acquire();
            System.out.println(Thread.currentThread().getName() + " acquire lock success");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //释放锁
            System.out.println(Thread.currentThread().getName() + "do release lock");
            try {
                lock.release();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + " release lock success");
    }

}
