package com.bmsoft.rmi;


import com.bmsoft.rmi.HelloService;
import com.bmsoft.rmi.client.rpc.RpcClientProxy;
import com.bmsoft.rmi.client.rpc.zk.IServiceDiscovery;
import com.bmsoft.rmi.client.rpc.zk.ServiceDiscoveryImpl;
import com.bmsoft.rmi.client.rpc.zk.ZkConfig;

public class ClientDemo {

    public static void main(String[] args) throws InterruptedException {
        IServiceDiscovery serviceDiscovery=new
                ServiceDiscoveryImpl(ZkConfig.CONNNECTION_STR);

        RpcClientProxy rpcClientProxy=new RpcClientProxy(serviceDiscovery);

        for(int i=0;i<10;i++) {
            HelloService hello = rpcClientProxy.clientProxy(HelloService.class, null);
            System.out.println(hello.sayHello("mic"));
            Thread.sleep(1000);
        }

    }
}
