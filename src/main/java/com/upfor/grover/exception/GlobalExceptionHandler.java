package com.upfor.grover.exception;

import com.upfor.grover.result.CommonResult;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLSyntaxErrorException;

/**
 * 全局异常处理类
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = ApiException.class)
    public CommonResult<?> handle(ApiException e) {
        if (e.getCode() != null) {
            return new CommonResult<>(e.getCode());
        }
        return CommonResult.failed(e.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public CommonResult<?> handleValidException(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        String msg = null;
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                msg = fieldError.getField() + fieldError.getDefaultMessage();
            }
        }
        return CommonResult.validFailed(msg);
    }

    @ResponseBody
    @ExceptionHandler(value = BindException.class)
    public CommonResult<?> handleValidException(BindException e) {
        BindingResult bindingResult = e.getBindingResult();
        String msg = null;
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                msg = fieldError.getField() + fieldError.getDefaultMessage();
            }
        }
        return CommonResult.validFailed(msg);
    }

    @ResponseBody
    @ExceptionHandler(value = SQLSyntaxErrorException.class)
    public CommonResult<?> handleSQLSyntaxErrorException(SQLSyntaxErrorException e) {
        return CommonResult.failed(e.getMessage());
    }

}
