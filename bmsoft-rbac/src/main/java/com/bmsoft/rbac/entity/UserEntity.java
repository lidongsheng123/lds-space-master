package com.bmsoft.rbac.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author 李东升
 * @version 1.0.0
 * @ClassName UserEntity.java
 * @Description TODO
 * @createTime 2019年01月10日 18:05:00
 **/
@Table(name = "sys_user")
@Entity
@Data
public class UserEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer uid;

    @Column(name = "username", unique = true)
    private String username;//帐号

    @Column(name = "password")
    private String password; //密码;

    @Column(name = "name")
    private String name;//名称（昵称或者真实姓名，不同系统不同定义）

    @Column(name = "salt")
    private String salt;//加密密码的盐

    @Column(name = "state")
    private byte state;//用户状态,0:创建未认证（比如没有激活，没有输入验证码等等）--等待验证的用户 , 1:正常状态,2：用户被锁定.

    @ManyToMany(fetch = FetchType.EAGER)//立即从数据库中进行加载数据;
    @JoinTable(name = "sys_user_role", joinColumns = {@JoinColumn(name = "uid")}, inverseJoinColumns = {@JoinColumn(name = "roleId")})
    private List<SysRoleEntity> roleList;// 一个用户具有多个角色



    /**
     * 密码盐.
     * @return
     */
    public String getCredentialsSalt(){
        return this.username+this.salt;
    }
}
