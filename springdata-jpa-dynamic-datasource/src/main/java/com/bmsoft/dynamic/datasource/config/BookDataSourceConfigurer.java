package com.bmsoft.dynamic.datasource.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.Map;
import java.util.Properties;

/**
 * @program: lds-space-master
 * @description: 书籍datasource配置类
 * @author: 李东升
 * @create: 2019-04-04 12:05
 **/
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "entityManagerFactoryBook",//实体管理引用
        transactionManagerRef = "transactionManagerBook",//事务管理引用
        basePackages = {"com.bmsoft.dynamic.datasource.book"}) //设置书籍数据源所应用到的包
public class BookDataSourceConfigurer {

    //注入书籍数据源
    @Autowired
    @Qualifier("bookDataSource")
    private DataSource bookDataSource;

    //配置EntityManager实体
    @Primary
    @Bean(name = "entityManagerBook")
    public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
        return entityManagerFactoryBook(builder).getObject().createEntityManager();
    }


    //配置EntityManager工厂实体
    @Primary
    @Bean(name = "entityManagerFactoryBook")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBook(EntityManagerFactoryBuilder builder) {
        //设置应用creditDataSource的基础包名
        LocalContainerEntityManagerFactoryBean entityManagerFactory = builder
                .dataSource(bookDataSource)
                .packages(new String[]{"com.bmsoft.dynamic.datasource.user"})

                .persistenceUnit("userPersistenceUnit")
                .build();
        entityManagerFactory.setJpaProperties(jpaProperties);
        return entityManagerFactory;
    }

    //注入jpa配置实体
    @Resource
    private Properties jpaProperties;



    //配置事务
    @Primary
    @Bean(name = "transactionManagerBook")
    public PlatformTransactionManager transactionManagerBook(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(entityManagerFactoryBook(builder).getObject());
    }
}
