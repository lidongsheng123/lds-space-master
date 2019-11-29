package com.springboot.expand.repository;

import com.springboot.expand.pojo.Person;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author ：Mr.Li
 * @date ：Created in 2019/11/23 15:14
 */
@Repository
public class UserRepository {

    private final Map<String, Person> users = new ConcurrentHashMap();

    public Collection<Person> findAll() {
        return users.values();
    }
}
