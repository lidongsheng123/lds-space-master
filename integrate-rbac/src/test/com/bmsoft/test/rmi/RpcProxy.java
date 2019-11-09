package com.bmsoft.test.rmi;

import com.bmsoft.test.rmi.handler.RemoteInvocationHandler;

import java.lang.reflect.Proxy;

/**
 * @program: lds-space-master
 * @description: 动态代理
 * @author: 李东升
 * @create: 2019-03-28 17:04
 **/
public class RpcProxy {

    public <T> T clientProxy(final Class<T> interfaces,
                             final String host,
                             final int port) {
        return (T) Proxy.newProxyInstance(interfaces.getClassLoader(),
                new Class[]{interfaces}, new RemoteInvocationHandler(host,port));
    }
}
