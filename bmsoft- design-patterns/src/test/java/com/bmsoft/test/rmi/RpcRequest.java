package com.bmsoft.test.rmi;

import lombok.Data;

import java.io.Serializable;

/**
 * @program: lds-space-master
 * @description: 发送
 * @author: 李东升
 * @create: 2019-03-28 17:01
 **/
@Data
public class RpcRequest implements Serializable{

    private String  methodName;
    private String className;
    private Object[] paramters;


}
