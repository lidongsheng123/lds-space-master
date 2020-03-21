package com.activiti.process;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author ：Mr.Li
 * @date ：Created in 2020/3/4 16:19
 */
public class BaseTest {

    static ConcurrentHashMap<Object, Object> hashMap = new ConcurrentHashMap<>();

    public static void main(String[] args) {
            hashMap.put("key","mic");
    }
}
