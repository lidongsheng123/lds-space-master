package com.bmsoft.design.proxy.custom;

import java.lang.reflect.Method;

/**
 * @program: lds-space-master
 * @description: 增强类
 * @author: 李东升
 * @create: 2019-02-28 15:04
 **/
public interface BMInvocationHandler {
    /**
     * 方法调用
     */
    Object invoke(Object proxy, Method method, Object[] args) throws Throwable;
}
