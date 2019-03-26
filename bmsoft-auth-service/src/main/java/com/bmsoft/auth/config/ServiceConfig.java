package com.bmsoft.auth.config;

import com.bmsoft.auth.exception.CustomWebResponseExceptionTranslator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;


@Configuration
public class ServiceConfig {

    @Bean
    public WebResponseExceptionTranslator webResponseExceptionTranslator() {
        return new CustomWebResponseExceptionTranslator();
    }

}
