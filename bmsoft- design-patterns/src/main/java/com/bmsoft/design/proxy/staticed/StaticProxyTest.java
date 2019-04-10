package com.bmsoft.design.proxy.staticed;

import com.bmsoft.design.proxy.Son;

public class StaticProxyTest {

    public static void main(String[] args) {

        Father father = new Father(new Son());
        father.findLove();

    }
}
