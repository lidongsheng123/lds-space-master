package com.bmsoft.design.Strategy;

import com.bmsoft.design.Strategy.factory.PayMethodFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by 李东升 on 2019/1/30.
 */
public class TestStrategy {

    public static void main(String[] args) {
        Bill bill = new Bill();
        bill.additem(new Item("java", 50));
        bill.additem(new Item("javascript", 30));

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(CreditCard.class);
        context.register(DebitCard.class);
        context.refresh();

        bill.pay(Card.paymentMethoedMap.get("credit"));

        context.close();
    }
}
