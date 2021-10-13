package com.example.controller;

import com.example.entity.User;
import com.example.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/mybatis")
    public ResponseEntity<List<User>> checkmybatisTransaction() {
        return ResponseEntity.ok().body(userService.findAllUserUsingMybatis());
    }

    @GetMapping("/jpa")
    public ResponseEntity<List<User>> checkJpaTransaction() {
        return ResponseEntity.ok().body(userService.findAllUserUsingJpa());
    }
}
