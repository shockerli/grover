package com.upfor.grover.dto.request.user;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.upfor.grover.entity.User;
import lombok.Data;

/**
 * User list request params
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true) // 忽略未知属性，避免抛异常
public class UserListRequest {

    /**
     * UserID
     */
    private Long id;

    public Wrapper<User> wrapper() {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>(new User());
        if (id != null) {
            wrapper.eq(User::getId, id);
        }
        return wrapper;
    }

}
