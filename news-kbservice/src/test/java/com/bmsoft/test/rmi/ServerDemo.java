package com.bmsoft.test.rmi;

import com.bmsoft.test.rmi.service.impl.HelloServiceImpl;

/**
 * @program: lds-space-master
 * @description: 测试类
 * @author: 李东升
 * @create: 2019-03-28 17:40
 **/
public class ServerDemo {

    public static void main(String[] args) {
        HelloServiceImpl helloService = new HelloServiceImpl();
        RpcServer rpcServer = new RpcServer();
        rpcServer.publisher(helloService, 8888);
    }
}
