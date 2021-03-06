package com.env.web.service;

import com.env.web.entity.UserInfo;
import com.env.web.vo.UserInfoVo;

import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ${author}
 * @since 2020-03-30
 */
public interface UserInfoService extends IService<UserInfo> {

	UserInfo selectOne(QueryWrapper<UserInfo> queryWrapper);

	IPage<UserInfoVo> findListPage(Page<UserInfo> page, UserInfo userInfo);
	
	Map<Integer, String> getIdNameMap(Integer projectId);
	
}
