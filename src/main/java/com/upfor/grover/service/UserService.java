package com.upfor.grover.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.upfor.grover.dto.request.user.UserListRequestDTO;
import com.upfor.grover.entity.UserEntity;
import com.upfor.grover.mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;

@Service
public class UserService extends ServiceImpl<UserMapper, UserEntity> {

    public List<UserEntity> list(@NotNull UserListRequestDTO params) {
        return this.list(params.wrapper());
    }

}
