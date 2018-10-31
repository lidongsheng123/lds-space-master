package com.bmsoft.demo.service.impl;

import com.bmsoft.demo.service.IModifyService;
import com.bmsoft.framework.annotation.Service;

/**
 * @author 李东升
 * @version 1.0.0
 * @ClassName MemberServiceIMpl.java
 * @Description TODO
 * @createTime 2018年10月19日 09:59:00
 */
@Service
public class ModifyServiceIMpl implements IModifyService {

    /**
     * 增加
     */
    public String add(String name, String addr) {
        return "modifyService add,name=" + name + ",addr=" + addr;
    }

    /**
     * 修改
     */
    public String edit(Integer id, String name) {
        return "modifyService edit,id=" + id + ",name=" + name;
    }

    /**
     * 删除
     */
    public String remove(Integer id) {
        return "modifyService id=" + id;
    }
}
