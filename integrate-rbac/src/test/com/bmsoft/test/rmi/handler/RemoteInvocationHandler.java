package com.bmsoft.test.rmi.handler;

import com.bmsoft.test.rmi.RpcRequest;
import com.bmsoft.test.rmi.TcpTransport;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @program: lds-space-master
 * @description: 增强类
 * @author: 李东升
 * @create: 2019-03-28 17:07
 **/
public class RemoteInvocationHandler implements InvocationHandler {

    private String host;
    private int port;

    public RemoteInvocationHandler(String host, int port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        RpcRequest rpcRequest = new RpcRequest();
        rpcRequest.setClassName(method.getDeclaringClass().getName());
        rpcRequest.setMethodName(method.getName());
        rpcRequest.setParamters(args);
        TcpTransport tcpTransport = new TcpTransport(host, port);
        return tcpTransport.send(rpcRequest);
    }
}
