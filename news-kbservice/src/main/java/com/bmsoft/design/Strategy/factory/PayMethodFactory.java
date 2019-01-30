package com.bmsoft.design.Strategy.factory;

import com.bmsoft.design.Strategy.CreditCard;
import com.bmsoft.design.Strategy.DebitCard;
import com.bmsoft.design.Strategy.PaymentMethoed;

/**
 * Created by 李东升 on 2019/1/30.
 */
public class PayMethodFactory {

    public static PaymentMethoed getPaymentMethoed(String type) {
        switch (type) {
            case "credit":
                return new CreditCard();
            case "debit":
                return new DebitCard();
            default:
                throw new RuntimeException("not fund exction");
        }
    }
}
