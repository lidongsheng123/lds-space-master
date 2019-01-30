package com.bmsoft.design.Strategy;

/**
 * Created by 李东升 on 2019/1/30.
 */
public class DebitCard extends Card {
    @Override
    public String getType() {
        return "debit";
    }

    @Override
    protected void excuteTransaction(int cents) {
        System.out.println("doTransaction with debit " + cents);
    }
}
