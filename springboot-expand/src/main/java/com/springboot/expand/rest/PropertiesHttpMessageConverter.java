package com.springboot.expand.rest;

import com.springboot.expand.pojo.Person;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Properties;

/**
 * @author ：Mr.Li
 * @date ：Created in 2019/11/9 11:12
 */

public class PropertiesHttpMessageConverter extends AbstractHttpMessageConverter<Person> {

    public PropertiesHttpMessageConverter(){
        super(MediaType.valueOf("application/properties"));
        setDefaultCharset(Charset.forName("UTF-8"));
    }

    @Override
    protected boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(Person.class);
    }

    /**
     * 讲请求内容中 Properties 内容转化成 Person 对象
     * @param clazz
     * @param inputMessage
     * @return
     * @throws IOException
     * @throws HttpMessageNotReadableException
     */
    @Override
    protected Person readInternal(Class<? extends Person> clazz,
                                  HttpInputMessage inputMessage)
            throws IOException, HttpMessageNotReadableException {

        /**
         * person.id = 1
         * person.name = 小马哥
         */
        InputStream inputStream = inputMessage.getBody();

        Properties properties = new Properties();
        // 将请求中的内容转化成Properties
        properties.load(new InputStreamReader(inputStream,getDefaultCharset()));
        // 将properties 内容转化到 Person 对象字段中
        Person person = new Person();
        person.setId(Long.valueOf(properties.getProperty("person.id")));
        person.setName(properties.getProperty("person.name"));

        return person;
    }

    @Override
    protected void writeInternal(Person person, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {

        OutputStream outputStream = outputMessage.getBody();

        Properties properties = new Properties();

        properties.setProperty("person.id",String.valueOf(person.getId()));
        properties.setProperty("person.name",person.getName());


        properties.store(new OutputStreamWriter(outputStream,getDefaultCharset()),"Written by web server");

    }

}
