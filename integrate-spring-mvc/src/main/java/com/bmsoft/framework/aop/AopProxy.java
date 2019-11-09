package com.bmsoft.framework.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author 李东升
 * @version 1.0.0
 * @ClassName AopProxy.java
 * @Description
 * @createTime 2018年10月17日 16:00:00
 */
public class AopProxy implements InvocationHandler {

    private AopConfig config;
    private Object target;

    //把原生的对象传进来
    public Object getProxy(Object instance) {
        this.target = instance;
        Class<?> clazz = instance.getClass();
        return Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), this);
    }

    public void setConfig(AopConfig config) {
        this.config = config;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //拿到目标对象的方法
        Method m = this.target.getClass().getMethod(method.getName(), method.getParameterTypes());

        //在原始方法调用以前要执行增强的代码
        //这里需要通过原生方法去找，通过代理方法去Map中是找不到的
        if (config.contains(m)) {
            AopConfig.Aspect aspect = config.get(m);
            aspect.getPoints()[0].invoke(aspect.getAspect());
        }


        Object obj = method.invoke(this.target, args);


        //在原始方法调用以后要执行增强的代码
        if (config.contains(m)) {
            AopConfig.Aspect aspect = config.get(m);
            aspect.getPoints()[1].invoke(aspect.getAspect());
        }

        //将最原始的返回值返回出去
        return obj;
    }
}
