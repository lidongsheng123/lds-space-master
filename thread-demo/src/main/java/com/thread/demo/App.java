package com.thread.demo;

/**
 * @author ：Mr.Li
 * @date ：Created in 2020/3/21 9:39
 */
public class App {
    public volatile static boolean stop = false;  //加上volatile 程序运行完后，立马结束。

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            int i = 0;
            while (!stop) { //不满足
                i++;
            }
        });
        thread.start();
        Thread.sleep(1000);
        stop=true; //true

    }
}
