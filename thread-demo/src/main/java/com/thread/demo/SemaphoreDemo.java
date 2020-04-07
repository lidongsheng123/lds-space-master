package com.thread.demo;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author ：Mr.Li
 * @date ：Created in 2020/4/7 19:08
 */
public class SemaphoreDemo {


    static class  Car extends Thread{

        private int sum;
        private Semaphore semaphore;

        public  Car(int sum,Semaphore semaphore){
            this.sum=sum;
            this.semaphore=semaphore;
        }
        @Override
        public void run() {
            try {
                semaphore.acquire();
                System.out.println(sum+  ":抢到一个车位");
                TimeUnit.SECONDS.sleep(1);
                System.out.println(sum+": 开走了");
                semaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args)  {
        Semaphore semaphore = new Semaphore(5);

        for (int i = 0; i <100 ; i++) {
            new Car(i,semaphore).start();
        }
    }
}
