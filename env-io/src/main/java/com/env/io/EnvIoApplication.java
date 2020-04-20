package com.env.io;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync//这个注解启用了线程池
public class EnvIoApplication {

	public static void main(String[] args) {
		SpringApplication.run(EnvIoApplication.class, args);
	}

}
