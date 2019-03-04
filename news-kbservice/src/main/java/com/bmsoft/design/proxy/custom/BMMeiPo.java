package com.bmsoft.design.proxy.custom;

import com.bmsoft.design.proxy.PerSon;

import java.lang.reflect.Method;

/**
 * @program: lds-space-master
 * @description: 媒婆类
 * @author: 李东升
 * @create: 2019-02-28 15:58
 **/
public class BMMeiPo implements BMInvocationHandler {


    /**
     * 被代理的对象，把引用给保存下来
     */
    private PerSon target;

    public Object getInstance(PerSon target) throws Exception {
        this.target = target;

        Class<?> clazz = target.getClass();

        //用来生成一个新的对象（字节码重组来实现）
        return BMProxy.newProxyInstance(new BMClassLoader(), clazz.getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("我是媒婆：我要给你找对象，现在已经拿到你的需求");
        System.out.println("开始物色");

        method.invoke(this.target, args);

        System.out.println("如果合适的话，就准备办事");

        return null;
    }
}
