package com.bmsoft.rmi.client.rpc.handler;

import com.bmsoft.rmi.RpcRequest;
import com.bmsoft.rmi.client.rpc.TCPTransport;
import com.bmsoft.rmi.client.rpc.zk.IServiceDiscovery;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @program: lds-space-master
 * @description: 请求处理类
 * @author: 李东升
 * @create: 2019-04-11 17:11
 **/
public class RemoteInvocationHandler implements InvocationHandler {
    private IServiceDiscovery serviceDiscovery;

    private String version;

    public RemoteInvocationHandler(IServiceDiscovery serviceDiscovery,String version) {
        this.serviceDiscovery=serviceDiscovery;
        this.version=version;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //组装请求
        RpcRequest request=new RpcRequest();
        request.setClassName(method.getDeclaringClass().getName());
        request.setMethodName(method.getName());
        request.setParameters(args);

        //根据接口名称得到对应的服务地址
        String serviceAddress=serviceDiscovery.discover(request.getClassName());
        //通过tcp传输协议进行传输
        TCPTransport tcpTransport=new TCPTransport(serviceAddress);
        //发送请求
        return tcpTransport.send(request);
    }
}
