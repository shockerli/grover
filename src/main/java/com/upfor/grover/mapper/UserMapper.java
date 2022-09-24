package com.upfor.grover.mapper;

import com.upfor.grover.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * User单表的所有SQL操作
 * <p>
 * Tips:
 * 1) 一个Mapper内的方法名不可重名（不可重载）
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
