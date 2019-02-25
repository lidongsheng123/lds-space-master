package com.bmsoft.design.factory.abstr;

/**
 * @program: lds-space-master
 * @description: 抽象工厂模式测试类
 * @author: 李东升
 * @create: 2019-02-25 14:03
 **/
public class AbstractFactoryTest {

    public static void main(String[] args) {

        MilkFactory factory = new MilkFactory();

        //对于用户而言，更加简单了
        //用户只有选择的权利了，保证了程序的健壮性
        System.out.println(factory.getChunZhen());
    }
}
