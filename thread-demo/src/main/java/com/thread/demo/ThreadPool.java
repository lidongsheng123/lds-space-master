package com.thread.demo;

import sun.misc.Unsafe;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author ：Mr.Li
 * @date ：Created in 2020/3/31 14:49
 */
public class ThreadPool /*implements Runnable*/ {

    private static final Unsafe unsafe = Unsafe.getUnsafe();

      int a;

    public static void main(String[] args) {
      /*  ExecutorService executorService = Executors.newFixedThreadPool(10);
        ForkJoinPool pool = ForkJoinPool.commonPool();
        executorService.execute(new ThreadPool());*/

        ThreadPool threadPool = new ThreadPool();
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        threadPool.add();
    }

        public void add () {
            int b=0;
            System.out.println(a);
            System.out.println(b);
        }
   /* @Override
    public void run() {

    }*/
}
