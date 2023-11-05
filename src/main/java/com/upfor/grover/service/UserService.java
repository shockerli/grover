package com.upfor.grover.service;

import com.upfor.grover.dto.request.user.UserListRequestDTO;
import com.upfor.grover.entity.UserEntity;
import com.upfor.grover.mapper.UserMapper;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.List;

@Service
@Log
public class UserService {

    @Resource
    UserMapper userMapper;

    public UserEntity getById(@NotNull Long id) {
        return userMapper.selectById(id);
    }

    public List<UserEntity> list(@NotNull UserListRequestDTO params) {
        return userMapper.selectList(params.wrapper());
    }

}
