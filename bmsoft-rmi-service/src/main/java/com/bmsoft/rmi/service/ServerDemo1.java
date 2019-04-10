package com.bmsoft.rmi.service;

import com.bmsoft.rmi.service.rpc.RpcServer;
import com.bmsoft.rmi.service.rpc.zk.IRegisterCenter;
import com.bmsoft.rmi.service.rpc.zk.RegisterCenterImpl;

import java.io.IOException;

/**
 * 腾讯课堂搜索 咕泡学院
 * 加群获取视频：608583947
 * 风骚的Michael 老师
 */
public class ServerDemo1 {
    public static void main(String[] args) throws IOException {
        IRegisterCenter registerCenter = new RegisterCenterImpl();
        HelloService helloService2 = new HelloServiceImpl2();
        RpcServer rpcServer = new RpcServer(registerCenter, "127.0.0.1:8081");
        rpcServer.bind(helloService2);
        rpcServer.publisher();
        System.in.read();
    }
}
