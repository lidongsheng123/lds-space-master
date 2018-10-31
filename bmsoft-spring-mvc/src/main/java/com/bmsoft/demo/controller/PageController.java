package com.bmsoft.demo.controller;

import com.bmsoft.demo.service.IQueryService;
import com.bmsoft.framework.annotation.Autowired;
import com.bmsoft.framework.annotation.Controller;
import com.bmsoft.framework.annotation.RequestMapping;
import com.bmsoft.framework.annotation.RequestParam;
import com.bmsoft.framework.webmvc.ModelAndView;

import java.util.HashMap;
import java.util.Map;


/**
 * 公布接口url
 *
 * @author Tom
 */
@Controller
@RequestMapping("/")
public class PageController {

    @Autowired
    IQueryService queryService;

    @RequestMapping("/first.html")
    public ModelAndView query(@RequestParam("teacher") String teacher) {
        String result = queryService.query(teacher);
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("teacher", teacher);
        model.put("data", result);
        model.put("token", "123456");
        return new ModelAndView("first.html", model);
    }

}
