package com.bmsoft.design.Strategy;

/**
 * @program: lds-space-master
 * @description: 支付状态类
 * @author: 李东升
 * @create: 2019-03-01 17:48
 **/
public class PayState {

    private int code;
    private Object data;
    private String msg;

    public PayState(int code, String msg, Object data) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    @Override
    public String toString() {
        return ("支付状态：[" + code + "]," + msg + ",交易详情：" + data);
    }
}
