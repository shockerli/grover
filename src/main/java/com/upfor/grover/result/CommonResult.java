package com.upfor.grover.result;

import com.upfor.grover.enums.ErrorCode;
import com.upfor.grover.enums.ApiCode;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 通用返回结果
 *
 * @param <T>
 */
@Data
@ToString
@NoArgsConstructor
public class CommonResult<T> {

    private int code; // 错误码
    private String msg; // 错误信息
    private T data; // 返回数据

    /**
     * 基于ErrorCode的构造函数
     *
     * @param code {@link ErrorCode}
     */
    public CommonResult(ErrorCode code) {
        this.code = code.getCode();
        this.msg = code.getMsg();

        if (code.getArgs().length > 0) {
            try {
                this.msg = String.format(code.getMsg(), code.getArgs());
            } catch (Exception ignored) {
            }
        }
    }

    /**
     * 基于ErrorCode和错误信息的构造函数
     *
     * @param code {@link ErrorCode}
     * @param msg  错误信息
     */
    public CommonResult(ErrorCode code, String msg) {
        this.code = code.getCode();
        this.msg = code.getMsg();
        if (msg != null && !msg.isEmpty()) {
            this.msg = msg;
        }

        if (code.getArgs().length > 0) {
            try {
                this.msg = String.format(code.getMsg(), code.getArgs());
            } catch (Exception ignored) {
            }
        }
    }

    /**
     * 基于ErrorCode的构造函数，可携带额外的返回数据
     *
     * @param code {@link ErrorCode}
     * @param data 返回数据
     */
    public CommonResult(ErrorCode code, T data) {
        this.code = code.getCode();
        this.msg = code.getMsg();
        this.data = data;

        if (code.getArgs().length > 0) {
            try {
                this.msg = String.format(code.getMsg(), code.getArgs());
            } catch (Exception ignored) {
            }
        }
    }

    /**
     * 自定义错误码和错误信息
     *
     * @param code 错误码
     * @param msg  错误信息
     */
    public CommonResult(int code, String msg) {
        this.code = code;
        this.msg = msg;
        if (msg == null || msg.isEmpty()) {
            ErrorCode c = ApiCode.getByCode(code);
            if (c != null) {
                this.msg = c.getMsg();
            }
        }
    }

    /**
     * 自定义错误码、错误信息和返回数据
     *
     * @param code 错误码
     * @param msg  错误信息
     * @param data 返回数据
     */
    public CommonResult(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T> CommonResult<T> success() {
        return new CommonResult<>(ApiCode.SUCCESS);
    }

    public static <T> CommonResult<T> success(T data) {
        return new CommonResult<>(ApiCode.SUCCESS, data);
    }

    public static <T> CommonResult<T> failed() {
        return new CommonResult<>(ApiCode.FAILED);
    }

    public static <T> CommonResult<T> failed(ErrorCode code) {
        return new CommonResult<>(code);
    }

    public static <T> CommonResult<T> failed(ErrorCode code, String msg) {
        return new CommonResult<>(code, msg);
    }

    public static <T> CommonResult<T> failed(String msg) {
        return new CommonResult<>(ApiCode.FAILED.getCode(), msg);
    }

    public static <T> CommonResult<T> validFailed() {
        return new CommonResult<>(ApiCode.VALIDATE_FAILED);
    }

    public static <T> CommonResult<T> validFailed(String msg) {
        return new CommonResult<>(ApiCode.VALIDATE_FAILED.getCode(), msg);
    }

}
