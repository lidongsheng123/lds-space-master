package com.bmsoft.framework.context;

/**
 * @author 李东升
 * @version 1.0.0
 * @ClassName ApplicationContextAware.java
 * @Description TODO
 * @createTime 2018年10月31日 09:24:00
 */
public interface ApplicationContextAware {
    //用于设置Application  容器
    void setApplicationContext(ApplicationContext applicationContext);
}
