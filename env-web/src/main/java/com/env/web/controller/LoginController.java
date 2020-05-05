package com.env.web.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.env.web.common.ResponseApi;
import com.env.web.common.UserHelper;
import com.env.web.entity.UserInfo;
import com.env.web.mapper.UserInfoMapper;
import com.env.web.util.EncryptUtil;


/**
 * 登录Controller
 * 
 * @author zhaoxin
 *
 */
@Controller
@RequestMapping(value = "/login")
public class LoginController {
	
	@Resource
	private UserInfoMapper userInfoMapper;

	private Log log = LogFactory.getLog(this.getClass());

	/**
	 * 跳转到登录页面
	 * 
	 * @param url
	 * @return
	 */
	@RequestMapping("")
	public String view(String url, HttpSession session) {
		// 如果用户已经登录跳转到首页，否则跳转到登录页
		return UserHelper.userIsLogin(session) ? "index" : "login";
	}

	/**
	 * 验证用户名和密码是否匹配
	 * 
	 * @param session
	 * @param userName
	 * @param password
	 * @return
	 * @author zhaoxin
	 */
	@RequestMapping("/verify_password")
	@ResponseBody
	public ResponseApi verifyPassword(HttpSession session, UserInfo userInfo) {
		log.info("验证用户名和密码");
		ResponseApi responseApi = new ResponseApi();
		try {
			QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
			queryWrapper.eq("user_name", userInfo.getUserName());
			queryWrapper.eq("password", EncryptUtil.md5(userInfo.getPassword()));
			queryWrapper.eq("state", 0);
			UserInfo user = userInfoMapper.selectOne(queryWrapper);
			if(user == null) {
				responseApi.fail("登录信息异常");
			}else {
				session.setAttribute(UserHelper.user_key, user);
				UserInfo userResult = new UserInfo();
				userResult.setUserName(userInfo.getUserName());
				userResult.setId(userInfo.getId());
				userResult.setActualName(userInfo.getActualName());
				responseApi.success(userResult);
			}
		} catch (Exception e) {
			responseApi.fail("系统异常");
			log.error(e);
		}
		return responseApi;
	}

	/**
	 * 退出登录
	 * @author: 李正
	 * @param session
	 * @return      
	 * @return: String      
	 * @throws
	 */
	@RequestMapping(value = "/logout", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public ResponseApi logout(HttpSession session) {
		ResponseApi response = new ResponseApi();
		log.info("退出登录");
		session.invalidate();
		response.success();
		return response;
	}

}
