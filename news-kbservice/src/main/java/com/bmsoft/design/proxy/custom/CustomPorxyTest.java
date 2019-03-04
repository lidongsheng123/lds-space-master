package com.bmsoft.design.proxy.custom;

import com.bmsoft.design.proxy.PerSon;
import com.bmsoft.design.proxy.Son;

/**
 * Created by Tom on 2018/3/10.
 */
public class CustomPorxyTest {

    public static void main(String[] args) {

        try {
            PerSon obj = (PerSon) new BMMeiPo().getInstance(new Son());
            System.out.println(obj.getClass());
            obj.findLove();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
