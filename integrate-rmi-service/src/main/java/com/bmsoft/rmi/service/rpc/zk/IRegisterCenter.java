package com.bmsoft.rmi.service.rpc.zk;

/**
 * @program: lds-space-master
 * @description: 注册中心
 * @author: 李东升
 * @create: 2019-04-10 21:10
 **/
public interface IRegisterCenter {

    /**
     * 注册服务名称和服务地址
     * @param serviceName
     * @param serviceAddress
     */
    void register(String serviceName,String serviceAddress);
}
