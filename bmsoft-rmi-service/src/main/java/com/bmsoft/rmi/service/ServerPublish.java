package com.bmsoft.rmi.service;

import com.bmsoft.rmi.service.rpc.RpcServer;
import com.bmsoft.rmi.service.rpc.zk.IRegisterCenter;
import com.bmsoft.rmi.service.rpc.zk.RegisterCenterImpl;

import java.io.IOException;

/**
 * @program: lds-space-master
 * @description: 发布服务
 * @author: 李东升
 * @create: 2019-04-10 21:50
 **/
public class ServerPublish {

    public static void main(String[] args) throws IOException {
        HelloService helloService = new HelloServiceImpl();
        HelloService helloService2 = new HelloServiceImpl2();

        IRegisterCenter registerCenter = new RegisterCenterImpl();

        RpcServer rpcServer = new RpcServer(registerCenter, "127.0.0.1:8080");
        rpcServer.bind(helloService, helloService2);
        rpcServer.publisher();
        System.in.read();
    }
}
