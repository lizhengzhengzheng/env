package com.env.web.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.env.web.common.ResponseApi;
import com.env.web.common.ResponseList;
import com.env.web.common.UserHelper;
import com.env.web.entity.MenuInfo;
import com.env.web.entity.RoleInfo;
import com.env.web.entity.UserInfo;
import com.env.web.service.MenuInfoService;
import com.env.web.service.RoleInfoService;
import com.env.web.vo.RoleInfoVo;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2020-03-30
 */
@RestController
@RequestMapping("/role")
public class RoleInfoController {
	
	private Log log = LogFactory.getLog(this.getClass());
	
	@Autowired
	private MenuInfoService menuInfoService;
	@Autowired
	private RoleInfoService roleInfoService;
	
	/**
	 * 拿到用户可见的菜单列表
	 * @author: 李正
	 * @param session
	 * @return      
	 * @return: ResponseApi      
	 * @throws
	 */
	@RequestMapping(value = "/getMenu", method = RequestMethod.POST)
	public ResponseApi getMenu(HttpSession session) {
		ResponseApi api = new ResponseApi();
		UserInfo user = UserHelper.getUserInfo(session);
		Collection<MenuInfo> muList = new ArrayList<>();
		QueryWrapper<MenuInfo> menuWrapper = new QueryWrapper<>();
		if(user.getCategory() == 1) {
			//只给项目管理
			menuWrapper.lambda().in(MenuInfo :: getId, List.of(50,51)).eq(MenuInfo :: getState, 0);
			muList = menuInfoService.list(menuWrapper);
		}else if(user.getCategory() == 2) {
			//除项目管理之外的其他全部
			menuWrapper.lambda().in(MenuInfo :: getId, List.of(1,2,3,4,11,15,16,17,18,19,21,22,23,24,25,26,28,30,35,36,37,38,42,
					46,50,51)).eq(MenuInfo :: getState, 0);
			muList = menuInfoService.list(menuWrapper);
		}else {
			Set<Integer> menuIds = new HashSet<>();
			QueryWrapper<RoleInfo> roleWrapper = new QueryWrapper<>();
			roleWrapper.lambda().in(RoleInfo :: getRoleId, Arrays.asList(user.getBackstageRoleList().split(",")));
			List<RoleInfo> list = roleInfoService.list(roleWrapper);
			if(list.size() != 0) {
				list.forEach(item -> {
					for (String s : item.getMenuList().split(",")) {
						menuIds.add(Integer.valueOf(s));
					}
				});
			}
			menuWrapper.lambda().in(MenuInfo :: getId, menuIds);
			muList = menuInfoService.list(menuWrapper);
	}
		api.success(muList);
		return api;
	}
	
	/**
	 * 角色列表
	 * @author: 李正
	 * @param page
	 * @param limit
	 * @param request
	 * @param session
	 * @return      
	 * @return: ResponseList      
	 * @throws
	 */
	@RequestMapping(value="/role_list", method=RequestMethod.POST)
	public ResponseList roleList(int page, int limit, HttpServletRequest request,HttpSession session){
		ResponseList responseList = new ResponseList();
        UserInfo user = UserHelper.getUserInfo(session);
        RoleInfo role = new RoleInfo();
        role.setState(0);
        role.setProjectId(user.getProjectId());
		try {			
			IPage<RoleInfoVo> list = roleInfoService.roleList(new Page<RoleInfo>(page,limit), role);
			responseList.success(list);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return responseList;
	}
	
	/**
	 * 获取角色选择框
	 * @param request
	 * @param session
	 * @return
	 * @author lz
	 */
	@RequestMapping(value="/selectRole", method=RequestMethod.POST)
	public ResponseApi selectRole(HttpServletRequest request,HttpSession session){
		ResponseApi responseApi = new ResponseApi();
        UserInfo user = UserHelper.getUserInfo(session);
		try {			
			QueryWrapper<RoleInfo> queryWrapper = new QueryWrapper<>();
			queryWrapper.eq("project_id", user.getProjectId());
			queryWrapper.eq("state", 0);
			List<RoleInfo> list = roleInfoService.list(queryWrapper);
			responseApi.success(list);
		} catch(Exception e) {
			log.error(e);
		}
		return responseApi;
	}

	/**
	 * 添加角色
	 * @author: 李正
	 * @param record
	 * @param session
	 * @param roleList
	 * @return      
	 * @return: ResponseApi      
	 * @throws
	 */
	@RequestMapping(value="/add_role", method=RequestMethod.POST)
	public ResponseApi addRole(RoleInfo record, HttpSession session){
		ResponseApi responseApi = new ResponseApi();
		if(!roleInfoService.isHave(session, 12)) {
			responseApi.fail("无权限");
			return responseApi;
		}
		UserInfo user = UserHelper.getUserInfo(session);
		record.setProjectId(user.getProjectId());
       try {
    	   roleInfoService.save(record);
			responseApi.success();
		   } catch(Exception e) {
			   log.error(e);
			   responseApi.fail("系统异常");
		  }
       return responseApi;
	}
	
	/**
	 * 编辑角色
	 * @author: 李正
	 * @param record
	 * @return      
	 * @return: ResponseApi      
	 * @throws
	 */
	@RequestMapping("/updata_role")
	public ResponseApi updataRole(RoleInfo record, HttpSession session){
		ResponseApi responseApi = new ResponseApi();
       try {
    	   if(!roleInfoService.isHave(session, 13)) {
   			responseApi.fail("无权限");
   			return responseApi;
   			}
    	   roleInfoService.updateById(record);
		   responseApi.success();
		   } catch(Exception e) {
			   log.error(e);
			   responseApi.fail("系统异常");
		  }
       return responseApi;
	}
	
	/**
	 * 删除角色
	 * @author lizheng
	 * @param @param roleInfo
	 * @param @return
	 * @return ResponseApi 返回类型
	 * @throws
	 */
	@RequestMapping("/delById")
	public ResponseApi delById(RoleInfo roleInfo, HttpSession session) {
		ResponseApi api = new ResponseApi();
		if(!roleInfoService.isHave(session, 14)) {
			api.fail("无权限");
   			return api;
   			}
		roleInfo.setState(1);
		roleInfoService.updateById(roleInfo);
		api.success();
		return api;
	}
	
}

