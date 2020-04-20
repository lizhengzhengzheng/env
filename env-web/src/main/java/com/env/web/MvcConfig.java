package com.env.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.env.web.aspect.UserLoginInterceptor;

@Configuration
public class MvcConfig implements WebMvcConfigurer{
	
	@Autowired
	private UserLoginInterceptor userLoginInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		//添加对用户是否登录的拦截器，并添加过滤项、排除项
		registry.addInterceptor(userLoginInterceptor).addPathPatterns("/**")
			.excludePathPatterns("/css/**","/js/**","/login/**","/layuiadmin/**")//排除样式、脚本、图片等资源文件
			.excludePathPatterns("/login/**");
	}

}
