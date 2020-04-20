package com.env.web.service;

import com.env.web.entity.RoleInfo;
import com.env.web.vo.RoleInfoVo;

import java.util.Map;

import javax.servlet.http.HttpSession;

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
public interface RoleInfoService extends IService<RoleInfo> {

	IPage<RoleInfoVo> roleList(Page<RoleInfo> page, RoleInfo role);
	
	Map<Integer, String> getIdNameMap(Integer projectId);
	
	boolean isHave(HttpSession session, int menuId);
	
}
