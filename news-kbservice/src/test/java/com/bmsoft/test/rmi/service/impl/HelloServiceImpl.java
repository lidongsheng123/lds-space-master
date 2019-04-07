package com.bmsoft.test.rmi.service.impl;

import com.bmsoft.test.rmi.service.HelloService;

/**
 * @program: lds-space-master
 * @description: 实现类
 * @author: 李东升
 * @create: 2019-03-28 16:44
 **/
public class HelloServiceImpl implements HelloService {

    @Override
    public String sayHello(String msg) {
        return "hello" + msg;
    }
}
