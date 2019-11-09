package com.bmsoft.test.rmi;

import com.bmsoft.test.rmi.service.HelloService;

/**
 * @program: lds-space-master
 * @description: 客户端
 * @author: 李东升
 * @create: 2019-03-28 16:47
 **/
public class ClientDemo {

    public static void main(String[] args) {
        RpcProxy rpcProxy = new RpcProxy();
        HelloService helloService = rpcProxy.clientProxy(HelloService.class, "localhost", 8888);
        System.out.println(helloService.sayHello("bbb"));
    }
}
