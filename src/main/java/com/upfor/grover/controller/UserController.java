package com.upfor.grover.controller;

import com.github.benmanes.caffeine.cache.Cache;
import com.upfor.grover.entity.UserEntity;
import com.upfor.grover.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Slf4j // 自动添加静态SLF4J日志记录器，支持多种日志框架
@RestController
@RequestMapping(path = "/api/user")
public class UserController {

    @Resource
    UserService userService;

    @Resource
    Cache<Long, UserEntity> userCacheById;

    @ResponseBody
    @GetMapping(value = "/{id}")
    public UserEntity getById(@PathVariable Long id) {
        log.info("Param: id={}", id);
        UserEntity user = userCacheById.getIfPresent(id);
        if (user == null) {
            user = userService.getById(id);
            if (user == null) {
                user = new UserEntity();
            }
            userCacheById.put(id, user);
        }
        return user;
    }

}
