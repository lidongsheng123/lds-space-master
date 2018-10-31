package com.bmsoft.framework.webmvc;

import lombok.Data;

import java.util.Map;

/**
 * @author 李东升
 * @version 1.0.0
 * @ClassName ModelAndView.java
 * @Description TODO
 * @createTime 2018年10月31日 11:19:00
 */
@Data
public class ModelAndView {

    private String viewName;
    private Map<String, ?> model;

    public ModelAndView(String viewName, Map<String, ?> model) {
        this.viewName = viewName;
        this.model = model;
    }
}
