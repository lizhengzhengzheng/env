package com.env.web.mapper;

import com.env.web.entity.SiteInfo;
import com.env.web.vo.SiteInfoVo;

import java.util.Collection;
import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since 2020-03-30
 */
public interface SiteInfoMapper extends BaseMapper<SiteInfo> {

	Collection<SiteInfoVo> findSiteList(SiteInfo siteInfo);

	List<String> selectCode(Integer projectId);

	IPage<SiteInfoVo> sitePage(Page<SiteInfo> page, SiteInfo site);

}
