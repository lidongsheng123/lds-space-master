package com.bmsoft.design.factory.abstr;

import com.bmsoft.design.factory.ChunZhenMilk;
import com.bmsoft.design.factory.Milk;
import com.bmsoft.design.factory.TeLunSuMilk;
import com.bmsoft.design.factory.func.ChunZhenFunctory;
import com.bmsoft.design.factory.func.TelunsuFactory;

/**
 * @program: lds-space-master
 * @description: 抽象工厂实现类
 * @author: 李东升
 * @create: 2019-02-25 14:04
 **/
public class MilkFactory extends AbstractFactory {

    @Override
    public Milk getChunZhen() {
        return new ChunZhenFunctory().getMilk();
    }

    @Override
    public Milk getTelunsu() {
        return new TelunsuFactory().getMilk();
    }
}
