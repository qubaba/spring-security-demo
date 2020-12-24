package com.example.part2imagecode.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @RequestMapping("login")
    public void login() {
        // throw new RuntimeException("登录异常");
    }

}
