package com.bmsoft.design.factory.func;

/**
 * @program: lds-space-master
 * @description: 工厂方法模式测试
 * @author: 李东升
 * @create: 2019-02-25 14:00
 **/
public class FactoryTest {
    public static void main(String[] args) {

        Factory factory = new TelunsuFactory();
        System.out.println(factory.getMilk());
    }
}
