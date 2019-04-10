package com.bmsoft.design.factory.abstr;

import com.bmsoft.design.factory.Milk;

/**
 * @program: lds-space-master
 * @description: 抽象工厂模式
 * @author: 李东升
 * @create: 2019-02-25 14:02
 **/
public abstract class AbstractFactory {
    //公共的逻辑
    //方便于统一管理

    /**
     * 获得一个蒙牛品牌的牛奶
     *
     * @return
     */
    public abstract Milk getChunZhen();


    /**
     * 获得一个特仑苏品牌的牛奶
     *
     * @return
     */
    public abstract Milk getTelunsu();


}
