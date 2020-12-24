package com.example.part2imagecode.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("app/user")
public class AppUserController {

    @RequestMapping("hello")
    public String hello() {
        return "app. hello";
    }
}
