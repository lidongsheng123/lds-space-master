package com.bmsoft.rmi.client.rpc.zk;


/**
 * @program: lds-space-master
 * @description: 服务发现
 * @author: 李东升
 * @create: 2019-04-10 22:21
 **/
public interface IServiceDiscovery {

    /**
     * 根据请求的服务地址，获得对应的调用地址
     *
     * @param serviceName
     * @return
     */
    String discover(String serviceName);
}
