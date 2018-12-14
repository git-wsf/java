package com.yangliuxin.config;


import com.yangliuxin.property.Property;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigApplicationProperty {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    Property property(){
        return new Property();
    }


}
