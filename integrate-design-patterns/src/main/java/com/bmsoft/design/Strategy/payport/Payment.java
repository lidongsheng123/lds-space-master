package com.bmsoft.design.Strategy.payport;


import com.bmsoft.design.Strategy.PayState;

/**
 * 支付渠道
 * Created by Tom on 2018/3/11.
 */
public interface Payment {

    PayState pay(String uid, double amount);

}
