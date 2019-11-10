package com.springboot.expand.controller;

import com.springboot.expand.pojo.Person;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：Mr.Li
 * @date ：Created in 2019/11/9 11:23
 */
@RestController
public class PersonRestController {


    @PostMapping(value = "/person/json/to/properties",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,//请求类型 Content-Type
            produces = "application/properties" // 响应类型
    )
    public Person personJsonToProperties(@RequestBody Person person) {
        // @RequestBody 的内容是 JSON
        // 响应的内容是 Properties
        return person;
    }

    @PostMapping(value = "/person/propertiesToJson",
            consumes = "application/properties", //
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE// 响应类型 // Accept
    )
    public Person personPropertiesToJson(@RequestBody Person person) {
        // @RequestBody 的内容是 Properties
        // 响应的内容是 JSON
        return person;
    }

}
