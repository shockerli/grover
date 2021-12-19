package com.upfor.grover.mapper;

import com.upfor.grover.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * User单表的所有SQL操作
 */
@Mapper
public interface UserMapper {

    /**
     * 根据用户ID查询用户信息
     *
     * @param id 用户ID
     * @return 用户信息
     */
    UserEntity getById(@Param("id") Long id);

}
