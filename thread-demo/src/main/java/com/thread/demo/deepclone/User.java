package com.thread.demo.deepclone;

/**
 * @author ：Mr.Li
 * @date ：Created in 2020/4/22 22:14
 */
public class User implements Cloneable {

    private String name;
    private int  age;

    private Address address;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        User user = new User();
        user.setName("张三");
        user.setAge(10);
        Address address = new Address();
        address.setCountry("中国");

        User user1 = (User)user.clone();
        Address address1 = user1.getAddress();
        Address address2 = user.getAddress();
        System.out.println(address1==address2);
    }
}
