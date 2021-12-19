package com.upfor.grover.controller;

import com.upfor.grover.entity.UserEntity;
import com.upfor.grover.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping(path = "/api/user")
public class UserController {

    private final static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Resource
    UserService userService;

    @ResponseBody
    @GetMapping(value = "/{id}")
    public UserEntity getById(@PathVariable Long id) {
        logger.info("Param: id={}", id);
        return userService.getById(id);
    }

}
