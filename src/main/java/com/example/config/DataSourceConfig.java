package com.example.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import javax.sql.DataSource;


@Configuration
@PropertySource("classpath:application.yml")
@RequiredArgsConstructor
public class DataSourceConfig {

    @Primary
    @Bean(name = "masterDataSource")
    public DataSource masterDataSource() {
        return masterProperties().initializeDataSourceBuilder().build();
    }

    @Primary
    @Bean(name = "slaveDataSource")
    public DataSource slaveDataSource() {
        return masterProperties().initializeDataSourceBuilder().build();
    }

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.master.datasource")
    public DataSourceProperties masterProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.slave.datasource")
    public DataSourceProperties slaveProperties() {
        return new DataSourceProperties();
    }
}
