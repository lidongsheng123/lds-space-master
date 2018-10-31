package com.bmsoft.demo.service.impl;

import com.bmsoft.demo.service.IQueryService;
import com.bmsoft.framework.annotation.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 李东升
 * @version 1.0.0
 * @ClassName QueryServiceIMpl.java
 * @Description TODO
 * @createTime 2018年10月31日 14:29:00
 */
@Service
public class QueryServiceIMpl implements IQueryService {
    /**
     * 查询
     */
    public String query(String name) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = sdf.format(new Date());
        String json = "{name:\"" + name + "\",time:\"" + time + "\"}";
        return json;
    }
}
