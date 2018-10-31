package com.bmsoft.framework.context;

import com.bmsoft.framework.beans.BeanDefinition;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 李东升
 * @version 1.0.0
 * @ClassName DefaultListableBeanFactory.java
 * @Description TODO
 * @createTime 2018年10月17日 15:29:00
 */
public class DefaultListableBeanFactory extends AbstractApplicationContext {

    //beanDefinitionMap用来保存配置信息
    protected Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    protected void onRefresh() {

    }

    @Override
    protected void refreshBeanFactory() {

    }
}
