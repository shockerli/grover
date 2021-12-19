package com.upfor.grover.service;

import com.upfor.grover.entity.UserEntity;
import com.upfor.grover.mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.validation.constraints.NotEmpty;

@Service
public class UserService {

    @Resource
    UserMapper userMapper;

    public UserEntity getById(@NotEmpty Long id) {
        return userMapper.getById(id);
    }

}
