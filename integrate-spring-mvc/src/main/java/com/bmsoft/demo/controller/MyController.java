package com.bmsoft.demo.controller;

import com.bmsoft.demo.service.IModifyService;
import com.bmsoft.demo.service.IQueryService;
import com.bmsoft.framework.annotation.Autowired;
import com.bmsoft.framework.annotation.Controller;
import com.bmsoft.framework.annotation.RequestMapping;
import com.bmsoft.framework.annotation.RequestParam;
import com.bmsoft.framework.webmvc.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 公布接口url
 *
 * @author Tom
 */
@Controller
@RequestMapping("/web")
public class MyController {

    @Autowired
    IQueryService queryService;
    @Autowired
    IModifyService modifyService;

    @RequestMapping("/query.json")
    public ModelAndView query(HttpServletRequest request, HttpServletResponse response,
                              @RequestParam("name") String name) {
       
        String result = queryService.query(name);
        System.out.println(result);

        return out(response, result);
    }

    @RequestMapping("/add*.json")
    public ModelAndView add(HttpServletRequest request, HttpServletResponse response,
                            @RequestParam("name") String name, @RequestParam("addr") String addr) {
        String result = modifyService.add(name, addr);
        return out(response, result);
    }

    @RequestMapping("/remove.json")
    public ModelAndView remove(HttpServletRequest request, HttpServletResponse response,
                               @RequestParam("id") Integer id) {
        String result = modifyService.remove(id);
        return out(response, result);
    }

    @RequestMapping("/edit.json")
    public ModelAndView edit(HttpServletRequest request, HttpServletResponse response,
                             @RequestParam("id") Integer id,
                             @RequestParam("name") String name) {
        String result = modifyService.edit(id, name);
        return out(response, result);
    }


    private ModelAndView out(HttpServletResponse resp, String str) {
        try {
            resp.setContentType("text/html;charset=UTF-8");
            resp.getWriter().write(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
