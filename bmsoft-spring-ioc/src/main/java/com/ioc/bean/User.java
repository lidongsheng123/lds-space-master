package com.ioc.bean;

import lombok.Data;

/**
 * @program: lds-space-master
 * @description: 用户bean
 * @author: 李东升
 * @create: 2019-08-06 09:58
 **/
@Data
public class User {
    private String name;
    private long phoneNum;
    private Addr addr;

   /* public User(String name, long phoneNum, Addr addr) {
        this.name = name;
        this.phoneNum = phoneNum;
        this.addr = addr;
    }*/
}
