package com.bmsoft.auth.dto;

import com.bmsoft.auth.entity.Permission;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

/**
 * Created by keets on 2017/11/22.
 */
@Data
public class RolePermissionDTO {

    private Long relationId;

    private UUID roleId;

    private String name;

    private Timestamp updateTime;

    private String description;

    private List<Permission> permissions;
}
