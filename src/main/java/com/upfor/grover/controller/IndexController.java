package com.upfor.grover.controller;

import com.upfor.grover.config.AppConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class IndexController {

    @Value("${spring.application.name}")
    private String appName;

    @Resource
    AppConfig appConfig;

    @RequestMapping("")
    public String index() {
        return String.format("Welcome to %s's %s!", appConfig.getAuthor(), appName);
    }

}
