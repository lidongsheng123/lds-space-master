package com.bmsoft.rmi.service;

import com.bmsoft.rmi.service.rpc.anno.RpcAnnotation;

/**
 * @program: lds-space-master
 * @description: 业务实现类2
 * @author: 李东升
 * @create: 2019-04-10 21:06
 **/
@RpcAnnotation(value = HelloService.class,version = "2")
public class HelloServiceImpl2 implements HelloService {

    @Override
    public String sayHello(String msg) {
        return "I'm 8081 node ："+msg;
    }
}
