package com.env.web.service.impl;

import com.env.web.entity.MenuInfo;
import com.env.web.entity.RoleInfo;
import com.env.web.entity.UserInfo;
import com.env.web.mapper.MenuInfoMapper;
import com.env.web.service.MenuInfoService;
import com.env.web.service.RoleInfoService;
import com.env.web.vo.MenuTreeVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 后台菜单表 服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2020-04-06
 */
@Service
public class MenuInfoServiceImpl extends ServiceImpl<MenuInfoMapper, MenuInfo> implements MenuInfoService {
	
	@Autowired
	private MenuInfoMapper menuInfoMapper;
	@Autowired
	private RoleInfoService roleInfoService;
	
	@Override
	public List<MenuTreeVo> findMenuTree(UserInfo user) {
		// TODO Auto-generated method stub
		Set<Integer> menuIds = new HashSet<>();
		if(user.getCategory() == 2) {
			menuIds = Set.of(1,2,3,4,5,6,7,11,12,13,14,15,16,17,18,19,22,25,26,28,30,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,52);
		}else {
			QueryWrapper<RoleInfo> roleWrapper = new QueryWrapper<>();
			roleWrapper.lambda().in(RoleInfo :: getRoleId, Arrays.asList(user.getBackstageRoleList().split(",")));
			List<RoleInfo> list = roleInfoService.list(roleWrapper);
			if(list.size() != 0) {
				for (RoleInfo item : list) {
					for (String s : item.getMenuList().split(",")) {
						menuIds.add(Integer.valueOf(s));
					}
				}
			}
		}
		return menuInfoMapper.findMenuTree(menuIds);
	}

}
