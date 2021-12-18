package com.upfor.grover.mapper;

import com.upfor.grover.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * User单表的所有SQL操作
 */
@Mapper
public interface UserMapper {

    UserEntity getById(@Param("id") Long id);

}
