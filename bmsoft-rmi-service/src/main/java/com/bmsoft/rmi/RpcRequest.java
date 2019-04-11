package com.bmsoft.rmi;

import lombok.Data;

import java.io.Serializable;

/**
 * @program: lds-space-master
 * @description: 传输对象
 * @author: 李东升
 * @create: 2019-04-10 20:57
 **/
@Data
public class RpcRequest implements Serializable {

    /**
     * 传输类名称
     */
    private String className;
    /**
     * 方法名
     */
    private String methodName;
    /**
     * 参数
     */
    private Object[] parameters;

    /**
     * 版本号
     */
    private String version;
}
