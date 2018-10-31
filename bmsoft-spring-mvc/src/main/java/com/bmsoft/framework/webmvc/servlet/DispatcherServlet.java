package com.bmsoft.framework.webmvc.servlet;

import com.bmsoft.framework.context.ApplicationContext;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

/**
 * @author 李东升
 * @version 1.0.0
 * @ClassName DispatcherServlet.java
 * @Description 只是作为一个MVC的启动入口
 * @createTime 2018年10月17日 14:13:00
 */
public class DispatcherServlet extends HttpServlet {

    private  final String LOCATION = "contextConfigLocation";

    @Override
    public void init(ServletConfig config) throws ServletException {
        //相当于把IOC容器初始化了
        ApplicationContext context = new ApplicationContext(config.getInitParameter(LOCATION));
        initStrategies(context);
    }

    protected void initStrategies(ApplicationContext context) {

      /*  //有九种策略
        // 针对于每个用户请求，都会经过一些处理的策略之后，最终才能有结果输出
        // 每种策略可以自定义干预，但是最终的结果都是一致
        // ModelAndView

        // =============  这里说的就是传说中的九大组件 ================
        initMultipartResolver(context);//文件上传解析，如果请求类型是multipart将通过MultipartResolver进行文件上传解析
        initLocaleResolver(context);//本地化解析
        initThemeResolver(context);//主题解析

        *//** 我们自己会实现 *//*
        //GPHandlerMapping 用来保存Controller中配置的RequestMapping和Method的一个对应关系
        initHandlerMappings(context);//通过HandlerMapping，将请求映射到处理器
        *//** 我们自己会实现 *//*
        //HandlerAdapters 用来动态匹配Method参数，包括类转换，动态赋值
        initHandlerAdapters(context);//通过HandlerAdapter进行多类型的参数动态匹配

        initHandlerExceptionResolvers(context);//如果执行过程中遇到异常，将交给HandlerExceptionResolver来解析
        initRequestToViewNameTranslator(context);//直接解析请求到视图名

        *//** 我们自己会实现 *//*
        //通过ViewResolvers实现动态模板的解析
        //自己解析一套模板语言
        initViewResolvers(context);//通过viewResolver解析逻辑视图到具体视图实现

        initFlashMapManager(context);//flash映射管理器*/
    }
}
