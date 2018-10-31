package com.bmsoft.framework.context;

/**
 * @author 李东升
 * @version 1.0.0
 * @ClassName AbstractApplicationContext.java
 * @Description TODO
 * @createTime 2018年10月17日 15:28:00
 */
public abstract class AbstractApplicationContext {

    //提供给子类重写
    protected void onRefresh(){
        // For subclasses: do nothing by default.
    }

    protected abstract void refreshBeanFactory();
}
