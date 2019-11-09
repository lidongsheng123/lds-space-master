package com.bmsoft.distributed.lock.zookeeper;

import java.util.concurrent.CountDownLatch;

/**
 * @program: lds-space-master
 * @description: zookeeper分布式锁测试类
 * @author: 李东升
 * @create: 2019-04-10 14:52
 **/
public class LockTest {

    public static void main(String[] args) throws Exception{
        CountDownLatch countDownLatch=new CountDownLatch(10);
        for(int i=0;i<10;i++){
            new Thread(()->{
                try {
                    countDownLatch.await();
                    DistributedLock distributedLock=new DistributedLock();
                    //获得锁
                    distributedLock.lock();
                    Thread.sleep(1000);
                    //释放锁
                    distributedLock.unlock();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            },"Thread-"+i).start();
            countDownLatch.countDown();
        }
        System.in.read();
    }
}
