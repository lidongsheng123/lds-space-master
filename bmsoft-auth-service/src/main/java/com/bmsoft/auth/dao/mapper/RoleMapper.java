package com.bmsoft.auth.dao.mapper;

import com.bmsoft.auth.entity.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.UUID;


public interface RoleMapper {

    int deleteByPrimaryKey(@Param("id") UUID id);

    int insert(Role record);

    Role selectByPrimaryKey(@Param("id") UUID id);

    int updateByPrimaryKey(Role record);

    List<Role> selectAll();

}
