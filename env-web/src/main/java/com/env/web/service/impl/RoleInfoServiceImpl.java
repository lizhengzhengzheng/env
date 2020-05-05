package com.env.web.service.impl;

import com.env.web.common.UserHelper;
import com.env.web.entity.RoleInfo;
import com.env.web.entity.UserInfo;
import com.env.web.mapper.RoleInfoMapper;
import com.env.web.service.RoleInfoService;
import com.env.web.service.UserInfoService;
import com.env.web.vo.RoleInfoVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2020-03-30
 */
@Service
public class RoleInfoServiceImpl extends ServiceImpl<RoleInfoMapper, RoleInfo> implements RoleInfoService {
	
	@Autowired
	private RoleInfoMapper roleInfoMapper;
	@Autowired
	private UserInfoService userInfoService;
	
	@Override
	public IPage<RoleInfoVo> roleList(Page<RoleInfo> page, RoleInfo role) {
		// TODO Auto-generated method stub
		IPage<RoleInfoVo> pageList = roleInfoMapper.roleList(page, role);
		Map<Integer,String> userMap = userInfoService.getIdNameMap(role.getProjectId());
		for (RoleInfoVo vo : pageList.getRecords()) {
			vo.setCreateUserName(userMap.get(vo.getCreateUser()));
		}
		return pageList;
	}

	@Override
	public Map<Integer, String> getIdNameMap(Integer projectId) {
		// TODO Auto-generated method stub
		QueryWrapper<RoleInfo> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("project_id", projectId);
		queryWrapper.eq("state", 0);
		List<RoleInfo> list = roleInfoMapper.selectList(queryWrapper);
		Map<Integer, String> map = new HashMap<>();
		list.forEach(item -> {
			map.put(item.getRoleId(), item.getRoleName());
		});
		return map;
	}

	@Override
	public boolean isHave(HttpSession session, int menuId) {
		UserInfo user = UserHelper.getUserInfo(session);
		Set<Integer> menuIds = new HashSet<>();
		QueryWrapper<RoleInfo> roleWrapper = new QueryWrapper<>();
		roleWrapper.lambda().in(RoleInfo :: getRoleId, Arrays.asList(user.getBackstageRoleList().split(",")));
		List<RoleInfo> list = this.list(roleWrapper);
		if(list.size() != 0) {
			list.forEach(item -> {
				for (String s : item.getMenuList().split(",")) {
					menuIds.add(Integer.valueOf(s));
				}
			});
		}
		if(menuIds.contains(menuId) || user.getCategory() == 1 || user.getCategory() == 2)
			return true;
		else
			return false;
	}

}
