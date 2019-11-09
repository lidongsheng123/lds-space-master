package com.bmsoft.rbac.dao;

import com.bmsoft.rbac.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author 李东升
 * @version 1.0.0
 * @ClassName UserInfoRepository.java
 * @Description TODO
 * @createTime 2019年01月10日 18:45:00
 **/

public interface UserInfoRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByUsername(String username);
}
