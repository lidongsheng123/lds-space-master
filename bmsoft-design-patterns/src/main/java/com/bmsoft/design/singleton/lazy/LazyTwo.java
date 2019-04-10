package com.bmsoft.design.singleton.lazy;

/**
 * double check
 */
public class LazyTwo {

    private LazyTwo() {

    }

    private static LazyTwo lazy = null;

    public static LazyTwo getInstance() {
        if (lazy == null) {
            synchronized (LazyTwo.class) {
                if (lazy == null) {
                    lazy = new LazyTwo();
                }
            }
        }
        return lazy;
    }
}
