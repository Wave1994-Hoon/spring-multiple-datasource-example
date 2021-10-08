package com.example.mapper;

import com.example.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.List;


@Mapper
public interface UserMapper {

    List<User> findAll();

    int saveUser(@Param("name") String name);
}
