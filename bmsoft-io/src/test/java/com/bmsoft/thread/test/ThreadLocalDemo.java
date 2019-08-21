package com.bmsoft.thread.test;

/**
 * @program: lds-space-master
 * @description: 本地线程测试类
 * @author: 李东升
 * @create: 2019-08-21 15:18
 **/
public class ThreadLocalDemo {

    private static int num = 0;

    private static ThreadLocal<Integer> localNum = new ThreadLocal<Integer>() {
        protected Integer initialValue() {
            return 0;
        }
    };

    static class Index {
        int num;

        public void incr() {
            num++;
        }
    }

    static Index index = new Index();
    private static ThreadLocal<Index> indexLocal = new ThreadLocal<Index>() {
        protected Index initialValue() {
            return index;
        }
    };

    public static void main(String[] args) {

        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                num += 5;
                System.out.println(Thread.currentThread().getName() + ":" + num);
            }).start();
        }
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                int num = localNum.get().intValue() + 5;
                System.out.println(Thread.currentThread().getName() + ":" + num);
            }).start();
        }

        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                index = indexLocal.get();
                index.incr();
                System.out.println(Thread.currentThread().getName() + ":" + index.num);
            }).start();
        }
    }
}
