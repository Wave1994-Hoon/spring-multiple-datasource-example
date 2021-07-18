package com.example.service;

import com.example.entity.User;
import com.example.mapper.UserMapper;
import com.example.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    //
    @Transactional(readOnly = true)
    public List<User> findAllUserUsingJpa() {
        return userRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<User> findAllUserUsingMybatis() {
        return userMapper.findAll();
    }

}
