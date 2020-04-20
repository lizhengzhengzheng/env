package com.env.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@MapperScan("com.env.web.mapper")
@SpringBootApplication
public class EnvWebApplication {
	public static void main(String[] args) {
		
		try {
			SpringApplication.run(EnvWebApplication.class, args);
		} catch (Exception e) {
            e.printStackTrace();
        }	
	}
}
