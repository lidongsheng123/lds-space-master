package com.bmsoft.framework.webmvc;

import lombok.Data;

import java.lang.reflect.Method;
import java.util.regex.Pattern;

/**
 * @author 李东升
 * @version 1.0.0
 * @ClassName HandlerMapping.java
 * @Description TODO
 * @createTime 2018年10月17日 14:10:00
 */
@Data
public class HandlerMapping {
    private Object controller;
    private Method method;
    private Pattern pattern;  //url的封装

    public HandlerMapping(Pattern pattern, Object controller, Method method) {
        this.controller = controller;
        this.method = method;
        this.pattern = pattern;
    }
}
