package com.bmsoft.rbac.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * @author 李东升
 * @version 1.0.0
 * @ClassName SysRoleEntity.java
 * @Description TODO
 * @createTime 2019年01月10日 18:12:00
 **/
@Table(name = "sys_role")
@Entity
@Data
public class SysRoleEntity {

    @Id
    @GeneratedValue
    @Column(name = "roleId")
    private Integer roleId; // 编号

    @Column(name = "role")
    private String role; // 角色标识程序中判断使用,如"admin",这个是唯一的:

    @Column(name = "description")
    private String description; // 角色描述,UI界面显示使用

    @Column(name = "available")
    private Boolean available = Boolean.FALSE; // 是否可用,如果不可用将不会添加给用户

    // 用户 - 角色关系定义;
    @ManyToMany
    @JoinTable(name = "sys_user_role", joinColumns = {@JoinColumn(name = "roleId")}, inverseJoinColumns = {@JoinColumn(name = "uid")})
    private List<UserEntity> userInfos;// 一个角色对应多个用户

    //角色 -- 权限关系：多对多关系;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "sys_role_permission", joinColumns = {@JoinColumn(name = "roleId")}, inverseJoinColumns = {@JoinColumn(name = "permissionId")})
    private List<SysPermissionEntity> permissions;


}
