package com.example;

import com.example.entity.User;
import com.example.mapper.UserMapper;
import com.example.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.List;


@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@RequiredArgsConstructor
public class MultipleDatasourceApplication {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    public static void main(String[] args) {
        SpringApplication.run(MultipleDatasourceApplication.class, args);
    }

    @PostConstruct
    @Transactional
    public void init() {
        User user1 = new User();
        user1.setName("111");

        User user2 = new User();
        user2.setName("222");

        userRepository.save(user1);
        userRepository.save(user2);

    }
}
