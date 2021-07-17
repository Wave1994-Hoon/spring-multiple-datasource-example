package com.example.config.datasource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;


@Configuration
@Slf4j
public class SlaveDataSourceConfig {

    @Bean(name = "slaveDataSource")
    public DataSource slaveDataSource() {
        return new HikariDataSource(slaveHikariConfig());
    }

    @Bean(value = "slaveHikariConfig")
    @ConfigurationProperties(prefix="spring.slave.datasource.hikari")
    public HikariConfig slaveHikariConfig() {
        return new HikariConfig();
    }
}
