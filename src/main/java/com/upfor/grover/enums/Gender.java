package com.upfor.grover.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 性别
 */
public enum Gender {

    MALE(1, "Male"),
    FEMALE(2, "Female"),
    OTHER(3, "Other"),
    ;

    @JsonValue // Jackson 注解，标识JSON序列化时使用value
    @EnumValue // 通用枚举注解，标识数据库存储的值是value
    public final int value; // 枚举值
    public final String desc; // 枚举描述

    Gender(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

}
