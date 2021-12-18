package com.upfor.grover.mapper;

import com.upfor.grover.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * User单表的所有SQL操作
 */
@Mapper
public interface UserMapper {

    User getById(Long id);

}
