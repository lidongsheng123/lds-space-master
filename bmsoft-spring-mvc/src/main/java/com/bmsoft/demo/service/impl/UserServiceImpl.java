package com.bmsoft.demo.service.impl;

import com.bmsoft.demo.service.MemberService;
import com.bmsoft.demo.service.UserService;
import com.bmsoft.framework.annotation.Autowired;
import com.bmsoft.framework.annotation.Service;

/**
 * @author 李东升
 * @version 1.0.0
 * @ClassName UserServiceImpl.java
 * @Description TODO
 * @createTime 2018年10月17日 18:22:00
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    MemberService memberService;

    public void add() {
        memberService.select();
        System.out.println("添加方法");
    }

}
