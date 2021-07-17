package com.example.replication;

import com.zaxxer.hikari.HikariConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class DataSourceTest {

    @Autowired
    @Qualifier("masterHikariConfig")
    private HikariConfig masterHikariConfig;

    @Autowired
    @Qualifier("slaveHikariConfig")
    private HikariConfig slaveHikariConfig;

    @Autowired
    private Environment environment;

    @Test
    @DisplayName("Master_DataSource_테스트")
    void masterDataSourceTest() {
        // given
        String url = environment.getProperty("spring.master.datasource.hikari.jdbc-url");
        String username = environment.getProperty("spring.master.datasource.hikari.username");
        String driverClassName = environment.getProperty("spring.master.datasource.hikari.driver-class-name");

        // when


        // then
        assertThat(masterHikariConfig.isReadOnly()).isFalse();
        assertThat(masterHikariConfig.getJdbcUrl()).isEqualTo(url);
        assertThat(masterHikariConfig.getUsername()).isEqualTo(username);
        assertThat(masterHikariConfig.getDriverClassName()).isEqualTo(driverClassName);

    }

    @Test
    @DisplayName("Slave_DataSource_테스트")
    void slaveDataSourceTest() {
        // given
        String url = environment.getProperty("spring.slave.datasource.hikari.jdbc-url");
        String username = environment.getProperty("spring.slave.datasource.hikari.username");
        String driverClassName = environment.getProperty("spring.slave.datasource.hikari.driver-class-name");

        // when


        // then
        assertThat(slaveHikariConfig.isReadOnly()).isTrue();
        assertThat(slaveHikariConfig.getJdbcUrl()).isEqualTo(url);
        assertThat(slaveHikariConfig.getUsername()).isEqualTo(username);
        assertThat(slaveHikariConfig.getDriverClassName()).isEqualTo(driverClassName);

    }
}
