package com.test.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication(scanBasePackages = "com.test.demo",exclude = {
        DataSourceAutoConfiguration.class,
        //JpaRepositoriesAutoConfiguration.class,
        //HibernateJpaAutoConfiguration.class,
        //SpringDataWebAutoConfiguration.class
        //FlywayAutoConfiguration.class,
        }
)
@ServletComponentScan
@EnableAsync
//@Import({AppConfiguration.class})
public class DemoApplication {


    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}
