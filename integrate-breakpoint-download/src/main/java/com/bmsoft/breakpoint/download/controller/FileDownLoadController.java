package com.bmsoft.breakpoint.download.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: lds-space-master
 * @description: 文件下载controller
 * @author: 李东升
 * @create: 2019-05-08 13:58
 **/
@RestController
public class FileDownLoadController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpServletResponse response;

    @PostMapping("/download")
    public Map<String, Object> downLoad(@RequestBody Map<String,Object> params) {
        Map result = new HashMap<String, Object>();
        request.getHeader("");
        String url = (String)params.get("url");

        return result;
    }
}
