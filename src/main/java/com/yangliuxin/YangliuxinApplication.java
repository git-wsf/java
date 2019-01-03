package com.yangliuxin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.flyway.FlywayAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = "com.yangliuxin",exclude = {DataSourceAutoConfiguration.class, FlywayAutoConfiguration.class})
@EnableScheduling
public class YangliuxinApplication {
    public static void main(String[] args) {
        SpringApplication.run(YangliuxinApplication.class, args);
    }
}
