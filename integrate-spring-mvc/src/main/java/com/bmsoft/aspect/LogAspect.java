package com.bmsoft.aspect;

/**
 * @author 李东升
 * @version 1.0.0
 * @ClassName LogAspect.java
 * @Description 增强的方法
 * @createTime 2018年10月17日 16:05:00
 */
public class LogAspect {

    //在调用一个方法之前，执行before方法
    public void before() {
        //这个方法中的逻辑，是由我们自己写的
        System.out.println("Invoker Before Method!!!");
    }

    //在调用一个方法之后，执行after方法
    public void after() {
        System.out.println("Invoker After Method!!!");
    }
}
