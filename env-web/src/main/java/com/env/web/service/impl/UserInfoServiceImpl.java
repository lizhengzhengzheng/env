package com.env.web.service.impl;

import com.env.web.entity.UserInfo;
import com.env.web.mapper.UserInfoMapper;
import com.env.web.service.DictionaryTypeService;
import com.env.web.service.RoleInfoService;
import com.env.web.service.UserInfoService;
import com.env.web.vo.UserInfoVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {
	
	@Autowired
	private UserInfoMapper userInfoMapper;
	@Autowired
	private RoleInfoService roleInfoService;
	@Autowired
	private DictionaryTypeService dictionaryTypeService;
	
	@Override
	public UserInfo selectOne(QueryWrapper<UserInfo> queryWrapper) {
		// TODO Auto-generated method stub
		return userInfoMapper.selectOne(queryWrapper);
	}

	@Override
	public IPage<UserInfoVo> findListPage(Page<UserInfo> page, UserInfo userInfo) {
		// TODO Auto-generated method stub
		IPage<UserInfoVo> pageList = userInfoMapper.findListPage(page, userInfo);
		Map<Integer,String> roleMap = roleInfoService.getIdNameMap(userInfo.getProjectId());
		Map<Integer,String> userMap = getIdNameMap(userInfo.getProjectId());
		Map<Integer,String> areaMap = dictionaryTypeService.getDictionaryList(userInfo.getProjectId(),4);
		for (UserInfoVo vo : pageList.getRecords()) {
			vo.setCreateUserName(userMap.get(vo.getCreateUser()));
			
			String[] roleIds = vo.getBackstageRoleList().split(",");
			String roleNames = " ";
			for (String roleId : roleIds) {
				if(!roleId.equals("")) {
					roleNames += roleMap.get(Integer.valueOf(roleId)) + ",";
				}
			}
			vo.setRoleName(roleNames.substring(0,roleNames.length() - 1));
			
			String[] areaIds = vo.getSiteArea().split(",");
			String areaNames = " ";
			for (String areaId : areaIds) {
				if(!areaId.equals("")) {
					areaNames += areaMap.get(Integer.valueOf(areaId)) + ",";
				}
			}
			vo.setAreaName(areaNames.substring(0,areaNames.length() - 1));
			
		}
		return pageList;
	}

	@Override
	public Map<Integer, String> getIdNameMap(Integer projectId) {
		// TODO Auto-generated method stub
		QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
		if(projectId == 0) {
			queryWrapper.eq("state", 0);
		}
		List<UserInfo> list = userInfoMapper.selectList(queryWrapper);
		Map<Integer, String> map = new HashMap<>();
		list.forEach(item -> {
			map.put(item.getId(), item.getActualName());
		});
		return map;
	}

}
