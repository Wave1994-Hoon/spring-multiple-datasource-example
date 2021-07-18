package com.example.replication;

import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class DataSourceTest {

    @Autowired
    private Environment environment;

    @Test
    @DisplayName("Master_DataSource_테스트")
    void masterDataSourceTest(
            @Qualifier("masterDataSource") final DataSource masterDataSource
    ) {
        // given
        String url = environment.getProperty("spring.datasource.master..hikari.jdbc-url");
        String username = environment.getProperty("spring.datasource.master.hikari.username");
        String driverClassName = environment.getProperty("spring.datasource.master.hikari.driver-class-name");

        // when
        HikariDataSource hikariDataSource = (HikariDataSource) masterDataSource;

        // then
        verifyOf(url, username, driverClassName, hikariDataSource);

    }

    @Test
    @DisplayName("Slave_DataSource_테스트")
    void slaveDataSourceTest(
            @Qualifier("slaveDataSource") final DataSource slaveDataSource
    ) {
        // given
        String url = environment.getProperty("spring.datasource.slave.hikari.jdbc-url");
        String username = environment.getProperty("spring.datasource.slave.hikari.username");
        String driverClassName = environment.getProperty("spring.datasource.slave.hikari.driver-class-name");

        // when
        HikariDataSource hikariDataSource = (HikariDataSource) slaveDataSource;


        // then
        verifyOf(url, username, driverClassName, hikariDataSource);

    }

    private void verifyOf(String url, String username, String driverClassName, HikariDataSource hikariDataSource) {
        assertThat(hikariDataSource.isReadOnly()).isFalse();
        assertThat(hikariDataSource.getJdbcUrl()).isEqualTo(url);
        assertThat(hikariDataSource.getUsername()).isEqualTo(username);
        assertThat(hikariDataSource.getDriverClassName()).isEqualTo(driverClassName);
    }
}
