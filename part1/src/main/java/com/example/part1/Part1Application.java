package com.example.part1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@RestController
@MapperScan("com.example.part1.mapper")
public class Part1Application {

    public static void main(String[] args) {
        SpringApplication.run(Part1Application.class, args);
    }


}
