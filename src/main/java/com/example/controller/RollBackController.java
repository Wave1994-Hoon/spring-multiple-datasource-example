package com.example.controller;


import com.example.entity.User;
import com.example.mapper.UserMapper;
import com.example.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;


@RestController
@Transactional
@RequiredArgsConstructor
public class RollBackController {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    @PostMapping("/jpa/rollback")
    public ResponseEntity<User> checkJpaTransactionRollBack(
            @RequestParam @NotNull String username
    ) {
        User testUser = new User();
        testUser.setName(username);

        userRepository.save(testUser);
        throw new RuntimeException("RollBack");

    }

    @PostMapping("/mybatis/rollback")
    public ResponseEntity<Integer> checkMyBatisTransactionRollBack(
            @RequestParam @NotNull String username
    ) {
        userMapper.saveUser(username);
        throw new RuntimeException("RollBack");
    }

    @PostMapping("/jpa")
    public ResponseEntity<Object> checkJpaTransaction(
            @RequestParam @NotNull String username
    ) {
        User testUser = new User();
        testUser.setName(username);

        userRepository.save(testUser);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/mybatis")
    public ResponseEntity<Object> checkMyBatisTransaction(
            @RequestParam @NotNull String username
    ) {
        userMapper.saveUser(username);
        return ResponseEntity.ok().build();
    }
}
