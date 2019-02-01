package com.bmsoft.design.Strategy;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 卡的抽象类
 */
@Component
public abstract class Card implements PaymentMethoed {

    static Map<String, PaymentMethoed> paymentMethoedMap = new ConcurrentHashMap<>();

    /**
     * 当所有的bean 初始化完毕调用此方法
     */
    @PostConstruct
    public void init() {
        paymentMethoedMap.put(getType(), this);
    }

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
