package com.bmsoft.rbac.service;

import com.bmsoft.rbac.dao.UserInfoRepository;
import com.bmsoft.rbac.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author 李东升
 * @version 1.0.0
 * @ClassName UserInfoService.java
 * @Description TODO
 * @createTime 2019年01月10日 18:46:00
 **/
@Service
@RequiredArgsConstructor
public class UserInfoService {


    private UserInfoRepository userInfoDao;

    public UserEntity findByUsername(String username) {
        System.out.println("UserInfoServiceImpl.findByUsername()");
        return userInfoDao.findByUsername(username);
    }
}
