package com.bmsoft.design.chain;

/**
 * @program: lds-space-master
 * @description: 请求处理器
 * @author: 李东升
 * @create: 2019-09-03 11:36
 **/
public interface RequestProcessor {
    //处理参数
    void processorRequest(Request request);
}
