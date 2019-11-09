package com.bmsoft.io.bio;

/**
 * @program: lds-space-master
 * @description: 计算机
 * @author: 李东升
 * @create: 2019-08-19 14:12
 **/

public class Calculator {

    public static int cal(String expression) throws Exception {
        char op = expression.charAt(1);
        switch (op) {
            case '+':
                return (expression.charAt(0) - 48) + (expression.charAt(2) - 48);
            case '-':
                return (expression.charAt(0) - 48) - (expression.charAt(2) - 48);
            case '*':
                return (expression.charAt(0) - 48) * (expression.charAt(2) - 48);
            case '/':
                return (expression.charAt(0) - 48) / (expression.charAt(2) - 48);
        }
        throw new Exception("Calculator error");
    }
}

