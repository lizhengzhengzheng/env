package com.env.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@MapperScan("com.env.web.mapper")
@SpringBootApplication
public class EnvWebApplication extends SpringBootServletInitializer {
	public static void main(String[] args) {
		
		try {
			SpringApplication.run(EnvWebApplication.class, args);
		} catch (Exception e) {
            e.printStackTrace();
        }	
	}
	
	//为springboot打包项目用的
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
	    // TODO Auto-generated method stub
	    return builder.sources(this.getClass());
	}
}
