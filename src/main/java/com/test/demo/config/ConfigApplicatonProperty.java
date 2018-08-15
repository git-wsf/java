package com.test.demo.config;


import com.test.demo.property.Property;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigApplicatonProperty {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    Property property(){
        return new Property();
    }


}
