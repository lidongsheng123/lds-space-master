package com.bmsoft.design.Strategy;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 李东升 on 2019/1/30.
 */
public class Bill {
    private List<Item> items = new ArrayList<>();

    public void additem(Item item) {
        items.add(item);
    }

    public void removeItem(Item item) {
        items.remove(item);
    }

    /**
     * 计算总价
     */
    public int getSumCents() {
        return items.stream().mapToInt(item -> item.getCents()).sum();
    }

    /**
     * 调用支付接口进行支付
     *
     * @param paymentMethoed
     */
    public void pay(PaymentMethoed paymentMethoed) {
        paymentMethoed.pay(getSumCents());
    }
}
