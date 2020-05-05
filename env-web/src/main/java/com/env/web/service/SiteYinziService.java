package com.env.web.service;

import com.env.web.entity.SiteYinzi;
import com.env.web.vo.SiteYinziVo;
import java.util.List;
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
public interface SiteYinziService extends IService<SiteYinzi> {

	List<SiteYinzi> findlist(Integer projectId);

	IPage<SiteYinziVo> findListPage(Page<SiteYinzi> page, SiteYinzi siteYinzi);

}
