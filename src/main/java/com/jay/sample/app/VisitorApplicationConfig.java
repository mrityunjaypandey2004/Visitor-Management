package com.jay.sample.app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

/**
 * Created by Jay on 8/30/2015.
 */
//@Component
@Configuration
public class VisitorApplicationConfig {

    @Bean
    public VisitorManagementService visitorService() {
        VisitorManagementService visitorService = new VisitorManagementServiceImpl();
        return visitorService;
    }

    @Bean
    public VisitorDao visitorDao() {
        VisitorDao visitorDao = new VisitorDao();
        return visitorDao;
    }

    @Bean
    public LoginDao loginDao() {
        LoginDao loginDao = new LoginDao();
        return loginDao;
    }

    @Bean
    public LoginService loginService() {
        LoginService loginService = new LoginServiceImpl();
        return loginService;
    }

    @Bean
    public DriverManagerDataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://ec2-52-76-26-160.ap-southeast-1.compute.amazonaws.com:3306/visitor_schema");
        dataSource.setUsername("root");
        dataSource.setPassword("password");
        return dataSource;
    }

}
