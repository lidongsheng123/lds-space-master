package com.bmsoft.framework.beans;

import lombok.Data;

/**
 * @author 李东升
 * @version 1.0.0
 * @ClassName BeanDefinition.java
 * @Description 类的描述信息
 * @createTime 2018年10月17日 14:47:00
 */
@Data
public class BeanDefinition {
    /**
     * bean 全类名
     */
    private String beanClassName;
    private boolean lazyInit = false;
    private String factoryBeanName;
}
