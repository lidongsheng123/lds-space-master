package com.bmsoft.demo.service.impl;

import com.bmsoft.demo.service.MemberService;
import com.bmsoft.framework.annotation.Service;

/**
 * @author 李东升
 * @version 1.0.0
 * @ClassName MemberServiceIMpl.java
 * @Description TODO
 * @createTime 2018年10月19日 09:59:00
 */
@Service
public class MemberServiceIMpl implements MemberService {
    @Override
    public void select() {
        System.out.println("id为"+1);
    }
}
