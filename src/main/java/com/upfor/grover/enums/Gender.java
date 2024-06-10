package com.upfor.grover.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;

import java.io.IOException;

/**
 * 性别
 */
@AllArgsConstructor
@JsonDeserialize(using = Gender.Deserializer.class)
public enum Gender {

    MALE(1, "Male"),
    FEMALE(2, "Female"),
    OTHER(3, "Other"),
    ;

    @JsonValue // Jackson 注解，标识JSON序列化时使用value
    @EnumValue // 通用枚举注解，标识数据库存储的值是value
    public final int value; // 枚举值
    public final String desc; // 枚举描述

    public static class Deserializer extends JsonDeserializer<Gender> {
        @Override
        public Gender deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
            int value = jsonParser.getIntValue();
            for (Gender item : Gender.values()) {
                if (item.value == value) {
                    return item;
                }
            }
            return null;
        }
    }

}
