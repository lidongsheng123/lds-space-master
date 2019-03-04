package com.bmsoft.design.proxy;

import com.bmsoft.design.proxy.PerSon;

/**
 * @program: lds-space-master
 * @description: 子类
 * @author: 李东升
 * @create: 2019-02-26 11:13
 **/
public class Son implements PerSon {

    @Override
    public void findLove() {
        //我没有时间
        //工作忙
        System.out.println("son---------找对象，肤白貌美大长腿");
    }

    @Override
    public void findJob() {
        System.out.println("son---------月薪20K-50k");
        System.out.println("son---------找工作");
    }
}
