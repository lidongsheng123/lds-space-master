package com.bmsoft.design.factory;

import com.bmsoft.design.factory.Milk;

/**
 * @program: lds-space-master
 * @description: 纯甄牛奶
 * @author: 李东升
 * @create: 2019-02-25 11:00
 **/
public class ChunZhenMilk implements Milk {
    @Override
    public String getName() {
        return "纯甄";
    }
}
