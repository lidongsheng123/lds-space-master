package com.bmsoft.design.Strategy.payport;


import com.bmsoft.design.Strategy.PayState;

/**
 * Created by Tom on 2018/3/11.
 */
public class JDPay implements Payment {

    @Override
    public PayState pay(String uid, double amount) {
        System.out.println("欢迎使用京东白条");
        System.out.println("查询账户余额，开始扣款");
        return new PayState(200, "支付成功", amount);
    }
}
