package com.example.second;

import com.alibaba.csp.sentinel.adapter.servlet.callback.WebCallbackManager;
import com.example.second.config.GlobalUrlBlockHandler;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
@EnableDubbo
@MapperScan(basePackages= {"com.example.second.dao","com.example.second.controller"})
public class SecondApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecondApplication.class, args);
	}

	@PostConstruct
	public void init(){
		WebCallbackManager.setUrlBlockHandler(new GlobalUrlBlockHandler());
	}

}
