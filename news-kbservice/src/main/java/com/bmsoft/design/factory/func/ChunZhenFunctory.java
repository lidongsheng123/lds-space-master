package com.bmsoft.design.factory.func;

import com.bmsoft.design.factory.ChunZhenMilk;
import com.bmsoft.design.factory.Milk;

/**
 * @program: lds-space-master
 * @description: 纯甄工厂
 * @author: 李东升
 * @create: 2019-02-25 13:58
 **/
public class ChunZhenFunctory implements Factory {

    @Override
    public Milk getMilk() {
        return new ChunZhenMilk();
    }
}
