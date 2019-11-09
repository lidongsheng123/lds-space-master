package com.bmsoft.juc.interrupt;

import java.util.concurrent.TimeUnit;

/**
 * @program: lds-space-master
 * @description: 线程复位demo
 * @author: 李东升
 * @create: 2019-09-03 16:47
 **/
public class ThreadResetDemo {

    public static void main(String[] args) throws InterruptedException {
        Thread thread=new Thread(()->{
            while(true){//默认是false  _interrupted state?
                if(Thread.currentThread().isInterrupted()){
                    System.out.println("before:"+Thread.currentThread().isInterrupted());
                    Thread.interrupted(); //复位- 回到初始状态
                    System.out.println("after:"+Thread.currentThread().isInterrupted());
                }
            }
        });
        thread.start();

        TimeUnit.SECONDS.sleep(1);
        thread.interrupt(); //把isInterrupted设置成true
    }
}
