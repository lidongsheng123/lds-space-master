package com.activiti.process.utils;

import lombok.Data;

import java.util.List;

/**
 * @author ：Mr.Li
 * @date ：Created in 2019/12/4 16:45
 */
@Data
public class DataGridView {

    private Integer code=0;
    private String msg="";
    private Long count;
    private List<?> data;

    public DataGridView(Long count, List<?> data) {
        this.count = count;
        this.data = data;
    }
}
