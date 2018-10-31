package com.bmsoft.test;

import com.alibaba.fastjson.JSON;
import com.bmsoft.dao.UserDao;
import com.bmsoft.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * @author 李东升
 * @version 1.0.0
 * @ClassName UserTest.java
 * @Description TODO
 * @createTime 2018年10月15日 16:13:00
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:application-context.xml"})
public class UserTest {


    @Autowired
    UserDao userDao;

    @Test
    public void testSelect() {

        try {
            List<User> users = userDao.selectByName("张三");
            System.out.println(JSON.toJSON(users).toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSelect01() {
        try {
            List<User> users = userDao.selectGTAge(10);
            System.out.println(JSON.toJSON(users).toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }






}
