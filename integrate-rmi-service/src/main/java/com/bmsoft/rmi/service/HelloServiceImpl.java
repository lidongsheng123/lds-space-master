package com.bmsoft.rmi.service;

import com.bmsoft.rmi.HelloService;
import com.bmsoft.rmi.service.rpc.anno.RpcAnnotation;

/**
 * @program: lds-space-master
 * @description: 业务实现类
 * @author: 李东升
 * @create: 2019-04-10 21:05
 **/
@RpcAnnotation(value = HelloService.class)
public class HelloServiceImpl implements HelloService {

    @Override
    public String sayHello(String msg) {
        return "I'm 8080 Node , " + msg;
    }
}
