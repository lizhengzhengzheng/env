package com.env.web.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.env.web.common.ResponseApi;
import com.env.web.common.UserHelper;
import com.env.web.entity.UserInfo;
import com.env.web.service.MenuInfoService;
import com.env.web.vo.MenuTreeVo;


/**
 * <p>
 * 后台菜单表 前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2020-04-06
 */
@RestController
@RequestMapping("/menu")
public class MenuInfoController {
	
	@Autowired
	private MenuInfoService menuInfoService;
	
	/**
	 * 权限树
	 * @author: 李正      
	 * @return: void      
	 * @throws
	 */
	@RequestMapping(value = "/getPermissions", method = RequestMethod.GET)
	public ResponseApi select_Permissions(HttpSession session) {
		ResponseApi responseApi = new ResponseApi();
		UserInfo user = UserHelper.getUserInfo(session);
		List<MenuTreeVo> list = menuInfoService.findMenuTree(user);
		responseApi.success(list);
		return responseApi;
	}

}

