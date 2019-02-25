package com.bmsoft.design.factory.simple;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @program: lds-space-master
 * @description: 简单工厂测试类
 * @author: 李东升
 * @create: 2019-02-25 11:02
 **/
public class SimpleFactoryTest {

    public static void main(String[] args) {
        /**
         * 把用户的需求告诉工厂
         * 创建产品的过程隐藏了，对于用户来说完全不知道是怎么产生的。
         */
        System.out.println(new SimpleFactory().getMilk("特仑苏").getName());
        System.out.println(new SimpleFactory().getMilk("纯甄").getName());

        /**
         * Spring 中的 BeanFactory 就是简单工厂模式的体现，根据传入一个唯一的标识来获得 Bean
         */
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:application-context.xml");
        System.out.println(context.getBean("user").toString());
    }
}
