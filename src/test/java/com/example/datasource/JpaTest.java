package com.example.datasource;

import com.example.config.datasource.DataSourceConfig;
import com.example.entity.User;
import com.example.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


import java.util.Optional;

import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.*;

@AutoConfigureTestDatabase(replace = Replace.NONE)
@DataJpaTest
@ImportAutoConfiguration({DataSourceConfig.class})
public class JpaTest {

    @Autowired
    UserRepository userRepository;

    @Test
    void test() {
        User user = userRepository.findUserByName("AAA").orElse(null);
        System.out.println(user);
    }
}
