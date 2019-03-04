package com.bmsoft.design.proxy.dynamic.cglib;

import com.bmsoft.design.proxy.PerSon;
import com.bmsoft.design.proxy.Son;

/**
 * @program: lds-space-master
 * @description: cglib 动态代理测试类
 * @author: 李东升
 * @create: 2019-02-28 14:45
 **/
public class CglibTest {

    public static void main(String[] args) {
        try {
            PerSon obj = (PerSon) new Cglib58().getInstance(Son.class);
            obj.findJob();
            System.out.println("--------------------------------");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
