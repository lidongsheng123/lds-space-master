package com.ioc.bean.test;

import com.ioc.bean.Addr;
import com.ioc.bean.User;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.lang.reflect.Field;

/**
 * @program: lds-space-master
 * @description: 测试类
 * @author: 李东升
 * @create: 2019-08-06 10:07
 **/
public class BeanTest {

    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException, InstantiationException {
       /* ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("classpath*:application-context.xml");
        User user = context.getBean(User.class);
        System.out.println(user.toString());*/
        Class<?> calzz = Class.forName("com.ioc.bean.User");
        Field field = calzz.getDeclaredField("name");
        Object o = calzz.newInstance();
        field.setAccessible(true);
        field.set(o, "长阿三");
        System.out.println(o.toString());

      /*  Addr addr = context.getBean(Addr.class);
        System.out.println(addr.toString());*/
    }}
