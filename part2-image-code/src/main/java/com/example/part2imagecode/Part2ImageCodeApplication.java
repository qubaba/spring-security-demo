package com.example.part2imagecode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.example.part2imagecode.*")
public class Part2ImageCodeApplication {

	public static void main(String[] args) {
		SpringApplication.run(Part2ImageCodeApplication.class, args);
	}

}
