package com.bmsoft.design.Strategy;

import lombok.Data;

/**
 * Created by 李东升 on 2019/1/30.
 */
@Data
public class Item {

    private String des;
    private int cents;

    public Item(String des, int cents) {
        this.des = des;
        this.cents = cents;
    }
}
