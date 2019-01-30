package com.bmsoft.design.Strategy;

import org.springframework.transaction.annotation.Transactional;

/**
 * 卡的抽象类
 */
public abstract class Card implements PaymentMethoed {

    @Override
    public void pay(int cents) {
        System.out.println("use" + getType() + "->payed" + cents + "cents");
        excuteTransaction(cents);
    }

    /**
     * 卡的类型
     */
    public abstract String getType();

    protected abstract void excuteTransaction(int cents);
}
