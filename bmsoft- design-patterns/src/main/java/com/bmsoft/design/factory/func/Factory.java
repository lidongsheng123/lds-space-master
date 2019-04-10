package com.bmsoft.design.factory.func;

import com.bmsoft.design.factory.Milk;

/**
 * @program: lds-space-master
 * @description: 工厂方法模式
 * @author: 李东升
 * @create: 2019-02-25 13:55
 **/
public interface Factory {
    /**
     * 工厂具有生产产品技能，统一的产品出口。
     */
    Milk getMilk();
}
