package com.upfor.grover.controller;

import com.upfor.grover.config.AppConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
public class IndexController {

    @Resource
    private AppConfig appConfig;

    @RequestMapping("")
    public String index() {
        return String.format("Welcome to %s's %s!", appConfig.getAuthor(), appConfig.getAppName());
    }

}
