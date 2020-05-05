package com.env.io;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;


@SpringBootApplication
@EnableAsync//这个注解启用了线程池
public class EnvIoApplication extends SpringBootServletInitializer {
	
	 private static ApplicationContext applicationContext;  

	public static void main(String[] args) {
		applicationContext = SpringApplication.run(EnvIoApplication.class, args);
	}
	
	public static ApplicationContext getApplicationContext(){  
        return applicationContext;  
    }
	
	public static Object getBean(String name){  
        return getApplicationContext().getBean(name);  
    }  
	
	//为springboot打包项目用的
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
	    // TODO Auto-generated method stub
	    return builder.sources(this.getClass());
	}

}
