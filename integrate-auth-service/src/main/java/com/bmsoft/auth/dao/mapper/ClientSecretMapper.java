package com.bmsoft.auth.dao.mapper;

import com.bmsoft.auth.entity.ClientSecret;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ClientSecretMapper {

    String getScope(@Param("clientId")String clientId,@Param("clientSecret")String clientSecret);

    int insert(ClientSecret record);

    List<ClientSecret> selectByParams(Map map);

    int updateByParams(Map map);

    int updateStatus(Map map);
}
