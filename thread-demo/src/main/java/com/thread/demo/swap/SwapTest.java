package com.thread.demo.swap;

import java.lang.reflect.Field;

/**
 * @author ：Mr.Li
 * @date ：Created in 2020/6/16 16:09
 */
public class SwapTest {

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {

       Integer a = 1, b = 2;
       swp2(a,b);
        System.out.println("a 的值为："+a);
        System.out.println("b 的值为："+b);

    }


    private static void swp2(Integer i1, Integer i2) throws NoSuchFieldException, IllegalAccessException {
        Field value = Integer.class.getDeclaredField("value");

        value.setAccessible(true);
        /*int tem=i1.intValue(); //tmp=1*/
         Integer tem=new Integer(i1.intValue());
        value.set(i1,i2); //因为 i2 是Integer 类型不需要进行Integer.valueOf()
        value.set(i2,tem);//tmp 是int 类型 因此 需要进行 Integer.valueOf(tmp) tmp=1  也就是在 缓存中取值；
    }

    /**
     *
     * @param i1
     * @param i2
     */
    private static void swp(Integer i1, Integer i2) {
       Integer tmp = i1;
        i1 = i2;
        i2= tmp;
    }
}
