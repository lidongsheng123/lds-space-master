package com.ioc.bean;

import lombok.Data;

/**
 * @program: lds-space-master
 * @description: 地址Bean
 * @author: 李东升
 * @create: 2019-08-06 10:01
 **/
@Data
public class Addr {

    private String street;

    private int id;

    public Addr(String street, int id) {
        this.street = street;
        this.id = id;
    }
}
