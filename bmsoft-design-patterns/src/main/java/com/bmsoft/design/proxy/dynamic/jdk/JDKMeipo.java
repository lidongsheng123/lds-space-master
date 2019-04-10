package com.bmsoft.design.proxy.dynamic.jdk;

import com.bmsoft.design.proxy.PerSon;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @program: lds-space-master
 * @description: JDK动态代理类
 * @author: 李东升
 * @create: 2019-02-28 14:24
 **/
public class JDKMeipo implements InvocationHandler {

    private PerSon target;

    public Object getInstance(PerSon target) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        this.target = target;

        Class<? extends PerSon> clazz = target.getClass();

        Method addd = clazz.getMethod("addd");

        return Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("我是媒婆：我要给你找对象，现在已经拿到你的需求");

        method.invoke(this.target, args);

        System.out.println("如果合适的话，就准备办事");
        return null;
    }
}
