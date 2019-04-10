package com.bmsoft.design.factory;

import com.bmsoft.design.factory.Milk;

/**
 * @program: lds-space-master
 * @description: 特仑苏牛奶类
 * @author: 李东升
 * @create: 2019-02-25 10:58
 **/
public class TeLunSuMilk implements Milk {
    @Override
    public String getName() {
        return "特仑苏";
    }
}
