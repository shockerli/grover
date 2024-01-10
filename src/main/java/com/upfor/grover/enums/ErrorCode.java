package com.upfor.grover.enums;

/**
 * 错误码接口
 */
public interface ErrorCode {

    int getCode();

    String getMsg();

    default Object[] getArgs() {
        return new Object[0];
    }

}
