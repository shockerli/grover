package com.upfor.grover.controller;

import com.upfor.grover.entity.User;
import com.upfor.grover.enums.ResultCode;
import com.upfor.grover.result.CommonResult;
import com.upfor.grover.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Slf4j // 自动添加静态SLF4J日志记录器，支持多种日志框架
@RestController
@RequestMapping(path = "/api/user")
public class UserController {

    @Resource
    private UserService userService;

    @ResponseBody
    @GetMapping(value = "/{id}")
    public CommonResult<?> getById(@PathVariable Long id) {
        log.info("Param: id={}", id);
        User user = userService.getById(id);
        if (user == null) {
            return CommonResult.failed(ResultCode.USER_NOT_EXIST);
        }
        return CommonResult.success(user);
    }

}
