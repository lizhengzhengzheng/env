package com.env.web.mapper;

import com.env.web.entity.SiteYinzi;
import com.env.web.vo.SiteYinziVo;

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
public interface SiteYinziMapper extends BaseMapper<SiteYinzi> {

	List<SiteYinzi> findlist(Integer projectId);

	IPage<SiteYinziVo> findListPage(Page<SiteYinzi> page, SiteYinzi siteYinzi);

}
