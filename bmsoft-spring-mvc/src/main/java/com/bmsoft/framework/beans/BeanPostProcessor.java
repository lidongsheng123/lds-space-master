package com.bmsoft.framework.beans;

/**
 * @author 李东升
 * @version 1.0.0
 * @ClassName BeanPostProcessor.java
 * @Description TODO
 * @createTime 2018年10月17日 15:55:00
 */
public class BeanPostProcessor {

    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        return bean;
    }
    public Object postProcessAfterInitialization(Object bean, String beanName){
        return bean;
    }
}
