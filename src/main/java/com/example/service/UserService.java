package com.example.service;

import com.example.entity.User;
import com.example.mapper.UserMapper;
import com.example.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * 실제 커넥션을 획득하면 중간에 속성을 바꿔도 다른 커넥션을 새로 맺지 않는다
 *  propagation 이 REQUIRED 일 경우에는 새로운 트랜잭션을 생성하지도 않고 새로운 설정을 적용하지도 않으므로 주의해야 한다
 */

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW)
    public List<User> findAllUserUsingJpa() {
        return userRepository.findAll();
    }

    @Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW)
    public List<User> findAllUserUsingMybatis() {
        return userMapper.findAll();
    }

}
