package com.example.part2imagecode.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("admin/user")
public class AdminUserController {

    @RequestMapping("hello")
    public String hello() {
        return "admin. hello";
    }
}
