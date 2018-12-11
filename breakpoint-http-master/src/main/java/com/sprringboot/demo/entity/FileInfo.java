package com.sprringboot.demo.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author 李东升
 * @version 1.0.0
 * @ClassName FileInfo.java
 * @Description TODO
 * @createTime 2018年11月28日 16:28:00
 */
@Data
@Entity
public class FileInfo implements Serializable {


    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String filename;

    @Column(nullable = false)
    private String identifier;

    @Column(nullable = false)
    private Long totalSize;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private String location;
}
