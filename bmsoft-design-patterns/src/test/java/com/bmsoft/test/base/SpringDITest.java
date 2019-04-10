package com.bmsoft.test.base;

import com.bmsoft.entity.Car;
import com.bmsoft.entity.User;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * @program: lds-space-master
 * @description: spring 依赖注入测试
 * @author: 李东升
 * @create: 2019-03-07 09:35
 **/
public class SpringDITest {

    public static void main(String[] args) {
      /*  //IOC容器初始化： BeanDefinition 的 Resource 定位、载入和注册
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:application-context.xml");
        //开始依赖注入
        Car car = (Car) context.getBean("car");
        System.out.println(car.toString());

        User user = context.getBean(User.class);
        System.out.println(user.toString());

        Car car1 = (Car) context.getBean("car1");
        System.out.println(car1.toString());*/

        DecimalFormat decimalFormat = new DecimalFormat("###,###.00");
        System.out.println(decimalFormat.format(1002200999.22323));

    }
}
