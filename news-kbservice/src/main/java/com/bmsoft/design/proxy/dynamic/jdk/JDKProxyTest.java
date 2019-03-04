package com.bmsoft.design.proxy.dynamic.jdk;

import com.bmsoft.design.proxy.PerSon;
import com.bmsoft.design.proxy.Son;

/**
 * @program: lds-space-master
 * @description: jdk动态代理测试类
 * @author: 李东升
 * @create: 2019-02-28 14:32
 **/
public class JDKProxyTest {

    public static void main(String[] args) {
        try {
            PerSon obj = (PerSon) new JDKMeipo().getInstance(new Son());
            System.out.println(obj.getClass());
            obj.findLove();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
