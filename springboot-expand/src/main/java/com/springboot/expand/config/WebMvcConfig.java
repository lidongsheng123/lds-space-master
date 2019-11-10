package com.springboot.expand.config;

import com.springboot.expand.rest.PropertiesHttpMessageConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @author ：Mr.Li
 * @date ：Created in 2019/11/9 11:21
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    /**
     * 扩展自定义协议体
     *
     * @param converters
     */
    public void extendMessageConverters(
            List<HttpMessageConverter<?>> converters) {

        converters.add(new PropertiesHttpMessageConverter());
    }

}
