package com.upfor.grover.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.upfor.grover.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * User单表的所有SQL操作
 * <p>
 * Tips:
 * 1) 一个Mapper内的方法名不可重名（不可重载）
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
