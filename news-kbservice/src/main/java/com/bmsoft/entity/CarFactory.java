package com.bmsoft.entity;

/**
 * @program: lds-space-master
 * @description: 工厂汽车类
 * @author: 李东升
 * @create: 2019-03-07 09:55
 **/
public class CarFactory {

    public Car createHongQiCar() {
        Car car = new Car();
        car.setBrand("红旗CA72");
        return car;
    }
}
