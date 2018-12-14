package com.yangliuxin.config;

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
        Flyway flyway = Flyway.configure().dataSource(dataSource).load();
        flyway.migrate();
        return flyway;
    }
}
