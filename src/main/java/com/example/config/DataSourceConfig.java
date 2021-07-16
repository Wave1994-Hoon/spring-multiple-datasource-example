package com.example.config;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;


@Configuration
@Slf4j
public class DataSourceConfig {

    @Primary
    @Bean(name = "masterDataSource")

    public DataSource masterDataSource() {
        System.out.println(masterProperties().getUrl());
        return masterProperties().initializeDataSourceBuilder().build();
    }

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
    @ConfigurationProperties(prefix = "spring.slave.datasource")
    public DataSourceProperties slaveProperties() {
        return new DataSourceProperties();
    }

    @Bean(name = "routingDataSource")
    public DataSource routingDataSource(
            @Qualifier("masterDataSource") DataSource masterDataSource,
            @Qualifier("slaveDataSource") DataSource slaveDataSource
    ) {
        ReplicationRoutingDataSource routingDataSource = new ReplicationRoutingDataSource();

        Map<Object, Object> dataSourceMap = new HashMap<>();

        dataSourceMap.put("master", masterDataSource);
        dataSourceMap.put("slave", slaveDataSource);

        routingDataSource.setTargetDataSources(dataSourceMap);
        routingDataSource.setDefaultTargetDataSource(masterDataSource);

        return routingDataSource;
    }

    @Bean(name = "dataSource")
    public DataSource dataSource(
            @Qualifier("routingDataSource") DataSource routingDataSource
    ) {
        return new LazyConnectionDataSourceProxy(routingDataSource);
    }
}
