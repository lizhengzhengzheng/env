package com.env.web.controller;

import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.env.web.common.ResponseApi;
import com.env.web.common.ResponseList;
import com.env.web.common.UserHelper;
import com.env.web.entity.UserInfo;
import com.env.web.service.RoleInfoService;
import com.env.web.service.UserInfoService;
import com.env.web.util.EncryptUtil;
import com.env.web.vo.UserInfoVo;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2020-03-30
 */
@RestController
@RequestMapping("/user")
public class UserInfoController {
	
	@Autowired
	private UserInfoService userInfoService;
	@Autowired
	private RoleInfoService roleInfoService;
	
	/**
	 *用户列表
	 * @author: 李正
	 * @param page
	 * @param limit
	 * @param userInfo
	 * @param session      
	 * @return: void      
	 * @throws
	 */
	@RequestMapping("/userList")
	public ResponseList userList(int page, int limit, UserInfo userInfo, HttpSession session) {
		UserInfo user = UserHelper.getUserInfo(session);
		userInfo.setProjectId(user.getProjectId());
		ResponseList responseList = new ResponseList();
		IPage<UserInfoVo> list = userInfoService.findListPage(new Page<UserInfo>(page,limit), userInfo);
		responseList.success(list);
		return responseList;
	}

	/**
	 * 获取用户下拉框信息
	 * @author: 李正
	 * @param userInfo
	 * @param session
	 * @return      
	 * @return: ResponseList      
	 * @throws
	 */
	@RequestMapping("/user_list")
	public ResponseApi userList(HttpSession session) {
		ResponseApi responseApi = new ResponseApi();
		UserInfo user = UserHelper.getUserInfo(session);
		QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
		if(user.getProjectId() != 0) {
			queryWrapper.eq("project_id", user.getProjectId());
		}
		queryWrapper.ne("state", 1);
		List<UserInfo> list = userInfoService.list(queryWrapper);
		responseApi.success(list);
		return responseApi;
	}
	
	/**
	 * 添加用户
	 * @author: 李正
	 * @param userInfo
	 * @param session      
	 * @return: void      
	 * @throws
	 */
	@RequestMapping("/addUser")
	public ResponseApi addUser(UserInfo userInfo, HttpSession session) {
		ResponseApi responseApi = new ResponseApi();
		if(!roleInfoService.isHave(session, 5)) {
			responseApi.fail("无权限");
			return responseApi;
		}
		QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("user_name", userInfo.getUserName());
		queryWrapper.eq("state", 0);
		UserInfo a = userInfoService.getOne(queryWrapper);
		if (a != null) {
			// 用户名已存在
			responseApi.fail("用户名已存在");
		} else {
			UserInfo user = UserHelper.getUserInfo(session);
			// 后台生成密码，默认为123456
			userInfo.setPassword(EncryptUtil.md5("123456"));
			userInfo.setProjectId(user.getProjectId());
			if(user.getCategory() == 3) {
				userInfo.setCategory(3);
			}else {
				userInfo.setCategory(user.getCategory() + 1);
			}
			userInfo.setCreateUser(user.getId());
			userInfoService.save(userInfo);
			responseApi.success();
		}
		return responseApi;
	}

	/**
	 * 编辑用户
	 * @author: 李正
	 * @param userInfo      
	 * @return: void      
	 * @throws
	 */
	@RequestMapping("/editUser")
	public ResponseApi editUser(UserInfo userInfo,HttpSession session) {
		UserInfo user = UserHelper.getUserInfo(session);
		ResponseApi responseApi = new ResponseApi();
		if(!roleInfoService.isHave(session, 6)) {
			responseApi.fail("无权限");
			return responseApi;
		}
		userInfoService.updateById(userInfo);
		responseApi.success();
		if(userInfo.getId() == user.getId()) {
			UserInfo lastest = userInfoService.getById(userInfo.getId());
			session.setAttribute(UserHelper.user_key, lastest);
		}
		return responseApi;
	}
	
	/**
	 * 更改密码
	 * @author: 李正
	 * @param oldPassword
	 * @param newPassword
	 * @param checkPassword
	 * @param session
	 * @return      
	 * @return: ResponseApi      
	 * @throws
	 */
	@RequestMapping("/editPassword")
	public ResponseApi editPassword(String oldPassword,String newPassword, HttpSession session) {
		ResponseApi api = new ResponseApi();
		UserInfo user = UserHelper.getUserInfo(session);
		if(!user.getPassword().equals(EncryptUtil.md5(oldPassword))) {
			//原始密码不正确
			api.fail("原始密码不正确");
		}else {
			UserInfo userInfo = new UserInfo(); 
			userInfo.setPassword(EncryptUtil.md5(newPassword));
			userInfo.setId(user.getId());
			userInfoService.updateById(userInfo);
			session.invalidate();
			api.success();
		}
		return api;
	}
	
	/**
	 * 删除用户
	 * @author lizheng
	 * @param @param userInfo
	 * @param @return
	 * @return ResponseApi 返回类型
	 * @throws
	 */
	@RequestMapping("/delById")
	public ResponseApi delById(UserInfo userInfo, HttpSession session) {
		ResponseApi api = new ResponseApi();
		if(!roleInfoService.isHave(session, 7)) {
			api.fail("无权限");
			return api;
		}
		userInfo.setState(1);
		userInfoService.updateById(userInfo);
		api.success();
		return api;
	}
	
	/**
	 * 获得当前登录的用户信息
	 * @author lizheng
	 * @param @param userInfo
	 * @param @return
	 * @return ResponseApi 返回类型
	 * @throws
	 */
	@RequestMapping("/getLoginUser")
	public ResponseApi getLoginUser(HttpSession session) {
		ResponseApi api = new ResponseApi();
		api.success(UserHelper.getUserInfo(session));
		return api;
	}
	
}

