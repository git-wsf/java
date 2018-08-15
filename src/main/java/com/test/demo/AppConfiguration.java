package com.test.demo;

import com.alibaba.druid.pool.DruidDataSource;
import com.test.demo.property.Property;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
@EnableJpaRepositories(basePackages= "com.test.demo.repository",
        entityManagerFactoryRef = "entityManagerFactory",
        transactionManagerRef = "transactionManager")
@EnableTransactionManagement
@EnableAutoConfiguration
public class AppConfiguration {

    private Logger logger = LoggerFactory.getLogger(AppConfiguration.class);

    @Autowired
    private Property property;


    @Bean
    @Primary
    public DataSource datasource(){
        DruidDataSource datasource = new DruidDataSource();

        datasource.setUrl(property.getUrl());
        datasource.setUsername(property.getUsername());
        datasource.setPassword(property.getPassword());
        datasource.setDriverClassName(property.getDriverClassName());

        //configuration
        datasource.setInitialSize(property.getInitialSize());
        datasource.setMinIdle(property.getMinIdle());
        datasource.setMaxActive(property.getMaxActive());
        datasource.setMaxWait(property.getMaxWait());
        datasource.setTimeBetweenEvictionRunsMillis(property.getTimeBetweenEvictionRunsMillis());
        datasource.setMinEvictableIdleTimeMillis(property.getMinEvictableIdleTimeMillis());
        datasource.setValidationQuery(property.getValidationQuery());
        datasource.setTestWhileIdle(property.getTestWhileIdle());
        datasource.setTestOnBorrow(property.getTestOnBorrow());
        datasource.setTestOnReturn(property.getTestOnReturn());
        datasource.setPoolPreparedStatements(property.getPoolPreparedStatements());
        datasource.setMaxPoolPreparedStatementPerConnectionSize(property.getMaxPoolPreparedStatementPerConnectionSize());
        try {
            datasource.setFilters(property.getFilters());
        } catch (SQLException e) {
            logger.error("druid configuration initialization filter", e);
        }
        datasource.setConnectionProperties(property.getConnectionProperties());

        return datasource;
    }



    @Autowired
    private JpaProperties jpaProperties;
//
    @Primary
    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder) {
        return builder.dataSource(datasource()).persistenceUnit("jpa").properties(jpaProperties.getProperties()).packages(new String[]{"com.test.demo.domain"}).build();
    }
//    public EntityManagerFactory entityManagerFactory() {
//        String persistenceUnitName = "jpa";
//        EntityManagerFactory factory= Persistence.createEntityManagerFactory(persistenceUnitName,jpaProperties.getProperties());
//
//        return factory;
//
////        String persistenceUnitName = "jpa";
////        EntityManagerFactory factory = Persistence.createEntityManagerFactory(persistenceUnitName);
////        return factory;
//        //LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
//        //localContainerEntityManagerFactoryBean.setPersistenceUnitName("persistenceUnit");
//        //return localContainerEntityManagerFactoryBean;
//    }


//
//
    @Primary
    @Bean(name = "transactionManager")
    public JpaTransactionManager transactionManager(EntityManagerFactoryBuilder builder) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory(builder).getObject());
        return transactionManager;
    }



//    @Bean(name = "flyway")
//    @Primary
//    public Flyway opFlyway(){
//        Flyway flyway = new Flyway();
//        flyway.setDataSource(datasource());
//        flyway.setEncoding("utf-8");
//        flyway.setTable("schema_demo_version");
//        flyway.setLocations("db/migration");
//
//        return flyway;
//    }


}