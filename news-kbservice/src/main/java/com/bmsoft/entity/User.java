package com.bmsoft.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author 李东升
 * @version 1.0.0
 * @ClassName User.java
 * @Description TODO
 * @createTime 2018年10月15日 15:58:00
 */
@Entity
@Table(name = "tb_user")
@Data
public class User implements Serializable {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private Integer age;

    public User(Long id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }
}
