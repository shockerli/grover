package com.upfor.grover.controller;

import com.upfor.grover.entity.User;
import com.upfor.grover.enums.ApiCode;
import com.upfor.grover.result.CommonResult;
import com.upfor.grover.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Slf4j // 自动添加静态SLF4J日志记录器，支持多种日志框架
@RestController
@RequestMapping(path = "/api/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping(value = "/{id}")
    @ResponseBody
    public CommonResult<?> getById(@PathVariable Long id) {
        log.info("Param: id={}", id);
        User user = userService.getById(id);
        if (user == null) {
            return CommonResult.failed(ApiCode.USER_NOT_EXIST);
        }
        return CommonResult.success(user);
    }

    @PostMapping(value = "/save")
    @ResponseBody
    public CommonResult<?> save(@RequestBody User user) {
        userService.saveOrUpdate(user);
        return CommonResult.success(user);
    }

}
