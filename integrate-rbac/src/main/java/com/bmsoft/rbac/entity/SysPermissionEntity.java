package com.bmsoft.rbac.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author 李东升
 * @version 1.0.0
 * @ClassName SysPermissionEntity.java
 * @Description TODO
 * @createTime 2019年01月10日 18:16:00
 **/
@Table(name = "sys_permission")
@Entity
@Data
public class SysPermissionEntity implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "permissionId")
    private Integer permissionId;//主键.

    @Column(name = "name")
    private String name;//名称

    @Column(columnDefinition = "enum('menu','button')")
    private String resourceType;//资源类型，[menu|button]

    @Column(name = "url")
    private String url;//资源路径.

    @Column(name = "permission")
    private String permission; //权限字符串,menu例子：role:*，button例子：role:create,role:update,role:delete,role:view

    @Column(name = "parentId")
    private Long parentId; //父编号

    @Column(name = "parentIds")
    private String parentIds; //父编号列表

    @Column(name = "available")
    private Boolean available = Boolean.FALSE;

    @ManyToMany
    @JoinTable(name = "sys_role_permission", joinColumns = {@JoinColumn(name = "permissionId")}, inverseJoinColumns = {@JoinColumn(name = "roleId")})
    private List<SysRoleEntity> roles;


}