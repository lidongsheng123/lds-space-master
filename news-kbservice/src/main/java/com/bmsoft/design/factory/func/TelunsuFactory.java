package com.bmsoft.design.factory.func;

import com.bmsoft.design.factory.Milk;
import com.bmsoft.design.factory.TeLunSuMilk;

/**
 * @program: lds-space-master
 * @description: 特仑苏工厂
 * @author: 李东升
 * @create: 2019-02-25 13:57
 **/
public class TelunsuFactory implements Factory {
    @Override
    public Milk getMilk() {
        return new TeLunSuMilk();
    }
}
