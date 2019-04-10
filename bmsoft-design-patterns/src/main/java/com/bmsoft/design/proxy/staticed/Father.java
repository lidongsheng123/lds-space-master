package com.bmsoft.design.proxy.staticed;

import com.bmsoft.design.proxy.PerSon;

/**
 * @program: lds-space-master
 * @description: 父类
 * @author: 李东升
 * @create: 2019-02-26 11:16
 **/
public class Father {

    private PerSon perSon;

    //没办法扩展
    public Father(PerSon perSon) {
        this.perSon = perSon;
    }

    //目标对象的引用给拿到
    public void findLove() {
        System.out.println("father++++++++++++++根据你的要求物色");
        this.perSon.findLove();
        System.out.println("father+++++++++++++双方父母是不是同意");
    }
}
