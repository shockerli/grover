package com.upfor.grover.enums;

/**
 * API Error Code
 */
public enum ErrorCode {

    DEFAULT(0, "Unknown Error"),
    OK(200, "OK");

    /**
     * Error Code
     */
    public final int code;

    /**
     * Error Message
     */
    public final String msg;

    ErrorCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * Get ErrorCode by code
     *
     * @param code Error Code
     * @return ErrorCode
     */
    public static ErrorCode get(int code) {
        for (ErrorCode v : ErrorCode.values()) {
            if (v.code == code) {
                return v;
            }
        }
        return null;
    }

    /**
     * Get ErrorCode by code, if not found, return default
     *
     * @param code Error Code
     * @return ErrorCode
     */
    public static ErrorCode getWithDefault(int code) {
        ErrorCode errorCode = get(code);
        if (errorCode == null) {
            return DEFAULT;
        }
        return errorCode;
    }

}
