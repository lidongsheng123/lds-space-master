package com.activiti.process.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
@Data
public class UserEntity {
    private Integer id;

    private String name;

    private String loginname;

    private String address;

    private Integer sex;

    private String remark;

    private String pwd;

    private Integer deptid;

    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date hiredate;

    private Integer mgr;

    private Integer available;

    private Integer ordernum;

    private Integer type;

    private String imgpath;

    private String salt;
    
    private String leadername;
    private String deptname;

}