package com.test.demo.config;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
@Configuration
public class FlywayConfiguration {


    @Autowired
    private DataSource dataSource;


    @Bean(name = "flyway")
    @Primary
    public Flyway opFlyway() {
        Flyway flyway = new Flyway();
        flyway.setDataSource(dataSource);
        flyway.setEncoding("utf-8");
        flyway.setTable("schema_demo_version");
        flyway.setLocations("db/migration");

        return flyway;
    }
}
