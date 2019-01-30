package com.bmsoft.design.Strategy;

/**
 * Created by 李东升 on 2019/1/30.
 */
public class CreditCard extends Card {


    @Override
    public String getType() {
        return "credit";
    }

    @Override
    protected void excuteTransaction(int cents) {
        System.out.println("doTransaction with credit " + cents);
    }
}
