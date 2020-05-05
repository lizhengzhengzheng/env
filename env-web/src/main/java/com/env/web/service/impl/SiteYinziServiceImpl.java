package com.env.web.service.impl;

import com.env.web.entity.SiteYinzi;
import com.env.web.mapper.SiteYinziMapper;
import com.env.web.service.SiteYinziService;
import com.env.web.vo.SiteYinziVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

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
public class SiteYinziServiceImpl extends ServiceImpl<SiteYinziMapper, SiteYinzi> implements SiteYinziService {
	
	@Autowired
	private SiteYinziMapper siteYinziMapper;
	
	@Override
	public List<SiteYinzi> findlist(Integer projectId) {
		// TODO Auto-generated method stub
		return siteYinziMapper.findlist(projectId);
	}

	@Override
	public IPage<SiteYinziVo> findListPage(Page<SiteYinzi> page, SiteYinzi siteYinzi) {
		// TODO Auto-generated method stub
		return siteYinziMapper.findListPage(page, siteYinzi);
	}

}
