package com.upfor.grover.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Result Code
 * <p>
 * 响应错误使用规范
 * <p>
 * 【注】msg文字相同不代表场景、含义相同，注意区分，以场景来区分code和error
 * <p>
 * 错误码规则<br>
 * 1. 除了基础公用Code，其他都是6位数字<br>
 * 2. 前面2位代码表示服务模块，从10-99<br>
 * 3. 中间2位代码表示业务模块，从01-99<br>
 * 4. 后面2位代码表示具体的细分业务错误信息，从01-99<br>
 */
@Getter
@AllArgsConstructor
public enum ResultCode implements ErrorCode {

    SUCCESS(200, "Success"),
    VALIDATE_FAILED(400, "Parameter validation failed"),
    FAILED(500, "Failed"),

    // 业务-用户相关（1001xx）
    USER_NOT_EXIST(100101, "User not exist"),

    ;

    @JsonValue // Jackson 注解，标识JSON序列化时使用的值
    @EnumValue // 通用枚举注解，标识数据库存储的值
    private final int code; // 错误码
    private final String msg; // 错误描述

    public static ResultCode getByCode(int code) {
        for (ResultCode resultCode : ResultCode.values()) {
            if (resultCode.getCode() == code) {
                return resultCode;
            }
        }
        return null;
    }

    public boolean isSuccess() {
        return this.code == SUCCESS.code;
    }

    public boolean isError() {
        return this.code != SUCCESS.code;
    }

}
