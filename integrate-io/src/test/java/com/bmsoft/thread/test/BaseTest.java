package com.bmsoft.thread.test;

import java.util.Observable;

/**
 * //订阅者模式
 *
 * @author ：Mr.Li
 * @date ：Created in 2019/12/12 14:31
 */
public class BaseTest {

    public static void main(String[] args) {
        MyObserver observable = new MyObserver();
        observable.addObserver((o, arg) -> System.out.println(arg));
        observable.setChanged();
        observable.notifyObservers("hello word!");
    }

    public static class MyObserver extends Observable {

        public synchronized void setChanged() {
            super.setChanged();
        }
    }
}
