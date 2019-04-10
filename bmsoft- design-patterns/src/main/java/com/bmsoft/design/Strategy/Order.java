package com.bmsoft.design.Strategy;

import com.bmsoft.design.Strategy.payport.PayType;

/**
 * @program: lds-space-master
 * @description: 订单类
 * @author: 李东升
 * @create: 2019-03-01 17:50
 **/
public class Order {
    private String uid;
    private String orderId;
    private double amount;

    public Order(String uid, String orderId, double amount) {
        this.uid = uid;
        this.orderId = orderId;
        this.amount = amount;
    }


    //完美地解决了switch的过程，不需要在代码逻辑中写switch了
    //更不需要写if    else if
    public PayState pay(PayType payType) {
        return payType.get().pay(this.uid, this.amount);
    }
}
