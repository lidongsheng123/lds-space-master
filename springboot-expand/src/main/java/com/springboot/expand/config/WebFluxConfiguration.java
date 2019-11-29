package com.springboot.expand.config;

import com.springboot.expand.pojo.Person;
import com.springboot.expand.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Flux;

import java.util.Collection;

/**
 * @author ：Mr.Li
 * @date ：Created in 2019/11/23 14:56
 */
@Configuration
public class WebFluxConfiguration {

    @Bean
    @Autowired
    public RouterFunction<ServerResponse> routerFunctionAllUsers(UserRepository userRepository) {
        Collection<Person> users = userRepository.findAll();
        Flux<Person> personFlux = Flux.fromIterable(users);
        return RouterFunctions.route(RequestPredicates.path("/users"),
                serverRequest -> ServerResponse.ok().body(personFlux, Person.class)
        );
    }
}
