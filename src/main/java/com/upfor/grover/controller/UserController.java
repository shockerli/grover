package com.upfor.grover.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/user")
public class UserController {

    @GetMapping(value = "/{id}")
    public String getById(@PathVariable Long id) {
        return "ID: " + id;
    }

}
