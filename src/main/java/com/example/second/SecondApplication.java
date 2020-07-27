package com.example.second;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages= {"com.example.second.dao","com.example.second.controller"})
public class SecondApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecondApplication.class, args);
	}

}
