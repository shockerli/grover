package com.upfor.grover.dto.request.user;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.upfor.grover.entity.UserEntity;
import lombok.Data;

/**
 * User List Request DTO
 */
@Data
public class UserListRequestDTO {

    /**
     * UserID
     */
    @JsonProperty("id")
    private Long id;

    public Wrapper<UserEntity> wrapper() {
        LambdaQueryWrapper<UserEntity> wrapper = new LambdaQueryWrapper<>(new UserEntity());
        if (id != null) {
            wrapper.eq(UserEntity::getId, id);
        }
        return wrapper;
    }

}
