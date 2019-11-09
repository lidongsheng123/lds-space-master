package com.bmsoft.design.proxy.dynamic.jdk;

import com.bmsoft.design.proxy.PerSon;
import com.bmsoft.design.proxy.Son;
import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;
import java.util.HashMap;

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

            obj.findLove();
            byte[] proxy0 = ProxyGenerator.generateProxyClass("$Proxy0", new Class[]{PerSon.class});
            FileOutputStream os = new FileOutputStream("E://$Proxy0.class");
            os.write(proxy0);
            os.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
