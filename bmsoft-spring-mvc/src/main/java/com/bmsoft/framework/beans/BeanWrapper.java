package com.bmsoft.framework.beans;

import com.bmsoft.framework.aop.AopConfig;
import com.bmsoft.framework.aop.AopProxy;
import com.bmsoft.framework.core.FactoryBean;

/**
 * @author 李东升
 * @version 1.0.0
 * @ClassName BeanWrapper.java
 * @Description TODO
 * @createTime 2018年10月17日 15:50:00
 */
public class BeanWrapper extends FactoryBean {

    private AopProxy aopProxy = new AopProxy();

    public BeanPostProcessor getPostProcessor() {
        return postProcessor;
    }

    public void setPostProcessor(BeanPostProcessor postProcessor) {
        this.postProcessor = postProcessor;
    }

    //还会用到  观察者  模式
    //1、支持事件响应，会有一个监听
    private BeanPostProcessor postProcessor;

    private Object wrapperInstance;
    //原始的通过反射new出来，要把包装起来，存下来
    private Object originalInstance;

    public BeanWrapper(Object instance) {
        //从这里开始，我们要把动态的代码添加进来了
        this.wrapperInstance = aopProxy.getProxy(instance);
        this.originalInstance = instance;
    }

    public Object getWrappedInstance() {
        return this.wrapperInstance;
    }


    // 返回代理以后的Class
    // 可能会是这个 $Proxy0
    public Class<?> getWrappedClass() {
        return this.wrapperInstance.getClass();
    }


    public void setAopConfig(AopConfig config) {
        aopProxy.setConfig(config);
    }


    public Object getOriginalInstance() {
        return originalInstance;
    }
}
