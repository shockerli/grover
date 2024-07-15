package com.upfor.grover.exception;

import com.upfor.grover.result.CommonResult;
import com.upfor.grover.enums.ErrorCode;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 基于错误码的Api Exception
 * <p>
 * 用于在业务层抛出异常，接口统一处理并返回统一结构
 * <p>
 * 用法：
 * <pre>
 * throw new ApiException(ApiCode.XXX);
 * throw new ApiException(ApiCode.XXX, data);
 * </pre>
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ApiException extends RuntimeException {

    private final ErrorCode code;

    private final String msg;

    private Object data;

    public ApiException(ErrorCode code) {
        super(code.getMsg());

        this.code = code;
        this.msg = code.getMsg();
    }

    public ApiException(ErrorCode code, Object data) {
        super(code.getMsg());

        this.code = code;
        this.msg = code.getMsg();
        this.data = data;
    }

    public ApiException(ErrorCode code, String msg) {
        super(msg);

        this.code = code;
        this.msg = msg;
    }

    public CommonResult<Object> toCommonResult() {
        return new CommonResult<>(code.getCode(), msg, data);
    }

}
