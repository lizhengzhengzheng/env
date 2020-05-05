package com.env.web.aspect;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import com.env.web.common.UserHelper;
import com.env.web.entity.UserInfo;

@Component
public class UserLoginInterceptor implements HandlerInterceptor {
		
	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)throws Exception {
		HttpSession session = request.getSession(true);
		UserInfo user = UserHelper.getUserInfo(session);
		if(null != user) {//已登录
			return true;
		}else {//未登录
			//直接重定向到登录页面
			response.sendRedirect("/login");
			return false;
		}
	}

}
