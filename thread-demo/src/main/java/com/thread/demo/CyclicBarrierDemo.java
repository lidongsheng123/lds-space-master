package com.thread.demo;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author ：Mr.Li
 * @date ：Created in 2020/4/7 19:26
 */
public class CyclicBarrierDemo {

    public static void main(String[] args) {


        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<String, String>();
        map.put("key","value");

        String  a[]={"a","b","c","D","e"};

        List<String> list = Arrays.asList(a);


       list.forEach(aa->{
           System.out.println(aa);
       });

        System.out.println(list.toString());
    }

}
