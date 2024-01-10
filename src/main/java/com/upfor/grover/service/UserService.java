package com.upfor.grover.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.upfor.grover.dto.request.user.UserListRequest;
import com.upfor.grover.entity.User;
import com.upfor.grover.mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;

@Service
public class UserService extends ServiceImpl<UserMapper, User> {

    public List<User> list(@NotNull UserListRequest params) {
        return this.list(params.wrapper());
    }

}
