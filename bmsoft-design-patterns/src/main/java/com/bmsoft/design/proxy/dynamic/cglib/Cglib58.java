package com.bmsoft.design.proxy.dynamic.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @program: lds-space-master
 * @description: cglib代理类
 * @author: 李东升
 * @create: 2019-02-26 11:53
 **/
public class Cglib58 implements MethodInterceptor {

    public Object getInstance(Class<?> clazz) throws Exception {
        Enhancer enhancer = new Enhancer();
        //要把哪个设置为即将生成的新类父类
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(this);
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {

        System.out.println("我是58：我要给你找工作，现在已经拿到你的简历");
        System.out.println("开始投递");

        methodProxy.invokeSuper(o, objects);

        System.out.println("安排面试");
        return null;
    }
}
