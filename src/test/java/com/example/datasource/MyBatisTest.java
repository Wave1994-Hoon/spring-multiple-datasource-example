package com.example.datasource;


import com.example.config.MyBatisConfig;
import com.example.config.datasource.DataSourceConfig;
import com.example.config.replication.RoutingDataSourceConfig;
import com.example.entity.User;
import com.example.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import java.util.List;

import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.*;


@AutoConfigureTestDatabase(replace = Replace.NONE)
@MybatisTest
@ImportAutoConfiguration({DataSourceConfig.class, MyBatisConfig.class, RoutingDataSourceConfig.class})
class MyBatisTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    void test() {
        List<User> users = userMapper.findAll();
        System.out.println(users);
    }
}
