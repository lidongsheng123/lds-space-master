package com.bmsoft.rmi.client.rmi;


import com.bmsoft.rmi.client.rmi.zk.IServiceDiscovery;
import com.bmsoft.rmi.client.rmi.zk.ServiceDiscoveryImpl;
import com.bmsoft.rmi.client.rmi.zk.ZkConfig;


public class ClientDemo {

    public static void main(String[] args) throws InterruptedException {
        IServiceDiscovery serviceDiscovery=new
                ServiceDiscoveryImpl(ZkConfig.CONNNECTION_STR);

    }
}
