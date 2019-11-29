package com.activiti.process.init;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * @author ：Mr.Li
 * @date ：Created in 2019/11/29 12:41
 */
public class InitTable {

    public static void main(String[] args) {
        DriverManagerDataSource dataSource =new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/activiti");
        dataSource.setUsername("root");
        dataSource.setPassword("root");

        ProcessEngineConfiguration processEngineConfiguration =
                ProcessEngineConfiguration.createStandaloneProcessEngineConfiguration();
        processEngineConfiguration.setJdbcDriver("com.mysql.jdbc.Driver");
        processEngineConfiguration.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/activiti");
        processEngineConfiguration.setJdbcUsername("root");
        processEngineConfiguration.setJdbcPassword("root");
        //processEngineConfiguration.setDataSource(dataSource);
        //没有activiti的表就创建。
        processEngineConfiguration.setDatabaseSchema("drop-create");
        //得到流程引擎对象
        ProcessEngine processEngine = processEngineConfiguration.buildProcessEngine();
        System.out.println(processEngine);

    }
}
