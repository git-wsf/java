package com.test.demo.property;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
//@ConfigurationProperties(prefix = "spring.datasource")
@Data
public class Property {

    private  String type;
    private  String driverClassName;
    private  String url;
    private  String username;
    private  String password;
    private  Integer initialSize;
    private  Integer minIdle;
    private  Integer maxActive;
    private  Integer maxWait;
    private  Integer timeBetweenEvictionRunsMillis;
    private  Integer minEvictableIdleTimeMillis;
    private  String validationQuery;
    private  Boolean testWhileIdle;
    private  Boolean testOnBorrow;
    private  Boolean testOnReturn;
    private  Boolean poolPreparedStatements;
    private  Integer maxPoolPreparedStatementPerConnectionSize;
    private  String filters;
    private  String connectionProperties;


}
