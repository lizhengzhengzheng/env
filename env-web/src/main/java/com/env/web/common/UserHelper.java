package com.env.web.common;

import javax.servlet.http.HttpSession;
import com.env.web.entity.UserInfo;

/**
 * <p>用户帮助类<p<
 * <p>功能描述：用于获取登录的用户</p>
 * @author zhaoxin
 *
 */
public class UserHelper {

	/**
	 * session的一个key，用来获取session的value
	 */
	public static final String user_key = "dsfjdsldsjfowoiefdslv";
	
	/**
	 * 获取用户信息
	 * @param session
	 * @return
	 * @author zhaoxin
	 */
	public static UserInfo getUserInfo(HttpSession session) {
		
		UserInfo userInfo = (UserInfo)session.getAttribute(user_key);
		return userInfo == null ? null: (UserInfo)userInfo;
	}
	
	/**
	 * 获取用户的id
	 * @param session
	 * @return
	 * @author zhaoxin
	 */
	public static Integer getUserId(HttpSession session) {
		UserInfo userInfo = getUserInfo(session);
		return userInfo == null ? null : userInfo.getId();
	}
	
	/**
	 * 判断用是否已经登录
	 * @param session
	 * @return true： 登录， false：未登录或者是session过期
	 */
	public static boolean userIsLogin(HttpSession session) {
		return getUserInfo(session) == null ? Boolean.FALSE : Boolean.TRUE;
	}
	
	/**
	 * 判断用是否没有登录
	 * @param session
	 * @return true： 没有登录或者是session失效， false：已经登录
	 */
	public static boolean userIsNotLogin(HttpSession session) {
		return !userIsLogin(session);
	}
}
