package com.bmsoft.design.Strategy;

import com.bmsoft.design.Strategy.factory.PayMethodFactory;

/**
 * Created by 李东升 on 2019/1/30.
 */
public class TestStrategy {

    public static void main(String[] args) {
        Bill bill = new Bill();
        bill.additem(new Item("java", 50));
        bill.additem(new Item("javascript", 30));
        bill.pay(PayMethodFactory.getPaymentMethoed("credit"));
    }
}
