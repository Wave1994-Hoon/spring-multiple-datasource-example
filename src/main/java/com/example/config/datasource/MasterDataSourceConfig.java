package com.example.config.datasource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;


@Configuration
@Slf4j
public class MasterDataSourceConfig {

    @Primary
    @Bean(name = "masterDataSource")
    public DataSource masterDataSource() {
        return new HikariDataSource(masterHikariConfig());
    }

    @Primary
    @Bean(value = "masterHikariConfig")
    @ConfigurationProperties(prefix="spring.master.datasource.hikari")
    public HikariConfig masterHikariConfig() {
        return new HikariConfig();
    }

}
