package com.bmsoft.dynamic.datasource.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * @program: lds-space-master
 * @description: 用户实体数据源配置
 * @author: 李东升
 * @create: 2019-04-04 12:16
 **/
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "entityManagerFactoryUser",//实体管理引用
        transactionManagerRef = "transactionManagerUser",//失误管理引用
        basePackages = {"com.bmsoft.dynamic.datasource.user"}) //设置用户数据源所应用到的包
public class UserDataSourceConfigurer {
    //注入用户数据源
    @Autowired
    @Qualifier("userDataSource")
    private DataSource userDataSource;

    //配置EntityManager实体
    @Bean(name = "entityManagerUser")
    public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
        return entityManagerFactoryUser(builder).getObject().createEntityManager();
    }

    //配置EntityManager工厂实体
    @Bean(name = "entityManagerFactoryUser")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryUser(EntityManagerFactoryBuilder builder) {
        //设置应用creditDataSource的基础包名
        LocalContainerEntityManagerFactoryBean entityManagerFactory = builder
                .dataSource(userDataSource)
                .packages(new String[]{"com.bmsoft.dynamic.datasource.user"})
                .persistenceUnit("userPersistenceUnit")
                .build();
        entityManagerFactory.setJpaProperties(jpaProperties);
        return entityManagerFactory;
    }

    //注入jpa配置实体
    @Autowired
    private Properties jpaProperties;


    //配置事务
    @Bean(name = "transactionManagerUser")
    public PlatformTransactionManager transactionManagerUser(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(entityManagerFactoryUser(builder).getObject());
    }

}
