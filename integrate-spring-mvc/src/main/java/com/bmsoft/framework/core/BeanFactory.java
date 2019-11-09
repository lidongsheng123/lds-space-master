package com.bmsoft.framework.core;

/**
 * @author 李东升
 * @version 1.0.0
 * @ClassName BeanFactory.java
 * @Description TODO
 * @createTime 2018年10月17日 12:43:00
 */
public interface BeanFactory {

    /**
     * 根据beanName从IOC容器之中获得一个实例Bean
     * @param beanName
     * @return
     */
    Object getBean(String beanName);
}
