package com.bmsoft.test;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: lds-space-master
 * @description: 基础测试
 * @author: 李东升
 * @create: 2019-04-19 10:44
 **/
public class BaseTest {


    public static void main(String[] args) {
        Integer a = 1000;
        Long b=48545615489454894L;
        Map<String, Object> map = new HashMap<>();
        map.put("fileSize", a);
        map.put("add",b);
        if (map.get("fileSize") instanceof Integer) {
            int fileSize = (int) map.get("fileSize");
            long o = (long) fileSize;
            System.out.println(o);
        }
        if (map.get("add") instanceof Long) {
            long fileSize = (long) map.get("add");
            System.out.println(fileSize);
        }
    }
}
