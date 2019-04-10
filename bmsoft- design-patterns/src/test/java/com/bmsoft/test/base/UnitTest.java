package com.bmsoft.test.base;

import com.bmsoft.lock.DistributedLock;
import com.bmsoft.lock.RedissonClient;
import org.junit.Test;
import org.redisson.api.RLock;

/**
 * @author 李东升
 * @version 1.0.0
 * @ClassName UnitTest.java
 * @Description TODO
 * @createTime 2018年10月26日 12:38:00
 */
public class UnitTest extends Thread {

    @Override
    public void run() {
        while (true) {
            DistributedLock distributedLock = new DistributedLock();
            String rs = distributedLock.acquireLock("updateOrder",
                    2000, 5000);
            if (rs != null) {
                System.out.println(Thread.currentThread().getName() + "-> 成功获得锁:" + rs);
                try {
                    Thread.sleep(1000);
                    distributedLock.releaseLock("updateOrder", rs);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }

    public static void main(String[] args) {
        UnitTest unitTest = new UnitTest();
        for (int i = 0; i < 10; i++) {
            new Thread(unitTest, "tName:" + i).start();
        }
    }


    @Test
    public  void  test01(){
        org.redisson.api.RedissonClient client = RedissonClient.getRedissonClient();
        RLock abc = client.getLock("abc");
        System.out.println(abc);
    }

}
