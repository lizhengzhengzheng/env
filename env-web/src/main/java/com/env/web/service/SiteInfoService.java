package com.env.web.service;

import com.env.web.entity.SiteInfo;
import com.env.web.vo.SiteInfoVo;

import java.util.Collection;
import java.util.List;
import java.util.Map;

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
public interface SiteInfoService extends IService<SiteInfo> {

	Collection<SiteInfoVo> findSiteList(SiteInfo siteInfo);

	List<String> selectCode(Integer projectId);

	IPage<SiteInfoVo> sitePage(Page<SiteInfo> page, SiteInfo site);
	
	Map<String,String> getCodeNameMap(Integer projectId);

}
