package com.activiti.process.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
@Data
public class LeaveBillEntity {
    private Integer id;

    private String title;

    private String content;

    private Double days;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date leavetime;
    //0未提交  1审批中  2审批完成  3  已放弃
    private String state;
    private Integer userid;
}