package com.example.service;

import com.example.entity.User;
import com.example.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
public class UserService {

    private UserRepository userRepository;

    @Transactional(readOnly = true)
    public List<User> findAllUser() {
        return userRepository.findAll();
    }

}
