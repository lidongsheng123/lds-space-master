package com.spring.expand.entity;

import lombok.Data;
import org.assertj.core.util.Lists;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author ：Mr.Li
 * @date ：Created in 2019/11/22 15:34
 */
@Data
public class User {
    private String name;
    private Integer age;
    private String createTime;

    public User(String name, Integer age, String createTime) {
        this.name = name;
        this.age = age;
        this.createTime = createTime;
    }

    public static void main(String[] args) {
        List<User> users = Lists.newArrayList(
                new User("jack", 17, "2019-11-22 15:40:31"),
                new User("jack", 17, "2019-11-20 15:51:31"),
                new User("jack", 17, ""),
                new User("jack", 17, "2019-11-25 15:51:31")
        );


        users.sort(Comparator.comparing(User::getCreateTime).reversed());
        System.currentTimeMillis();
       // System.out.println(users);

        ConcurrentHashMap<String, Object> map = new ConcurrentHashMap<>();
        Object put = map.put("1", 1);
        Object put1 = map.put("1", 1);

        Collection<Object> values = map.values();


    }
}
