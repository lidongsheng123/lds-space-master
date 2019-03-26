package com.bmsoft.auth.entity;

import com.bmsoft.auth.utils.TimestampAdapter;
import lombok.Data;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.sql.Timestamp;


@Data
public abstract class BaseEntity implements Serializable {

    private static final long serialVersionUID = 8388417013613884411L;

    @XmlJavaTypeAdapter(TimestampAdapter.class)
    private Timestamp createTime;

    @XmlJavaTypeAdapter(TimestampAdapter.class)
    private Timestamp updateTime;

    private int createBy;

    private int updateBy;
}
