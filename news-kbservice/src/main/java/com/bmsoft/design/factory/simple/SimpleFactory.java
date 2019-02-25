package com.bmsoft.design.factory.simple;

import com.bmsoft.design.factory.ChunZhenMilk;
import com.bmsoft.design.factory.Milk;
import com.bmsoft.design.factory.TeLunSuMilk;

/**
 * @program: lds-space-master
 * @description: 简单工厂模式工厂类
 * @author: 李东升
 * @create: 2019-02-25 10:54
 **/
public class SimpleFactory {

    public Milk getMilk(String nilkName) {
        if ("特仑苏".equals(nilkName)) {
            return new TeLunSuMilk();
        } else if ("纯甄".equals(nilkName)) {
            return new ChunZhenMilk();
        } else {
            System.out.println("不能生产您所需的产品");
            return null;
        }
    }
}
