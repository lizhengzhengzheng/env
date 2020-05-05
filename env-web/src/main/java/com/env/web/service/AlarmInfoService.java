package com.env.web.service;

import com.env.web.entity.AlarmInfo;
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
public interface AlarmInfoService extends IService<AlarmInfo> {

	IPage<AlarmInfo> findListPage(Page<AlarmInfo> page, Integer siteId, String startDate, String endDate, Integer type, Integer siteArea, Integer projectId);

}
