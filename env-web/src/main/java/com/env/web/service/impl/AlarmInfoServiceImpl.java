package com.env.web.service.impl;

import com.env.web.entity.AlarmInfo;
import com.env.web.mapper.AlarmInfoMapper;
import com.env.web.service.AlarmInfoService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

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
public class AlarmInfoServiceImpl extends ServiceImpl<AlarmInfoMapper, AlarmInfo> implements AlarmInfoService {
	
	@Autowired
	private AlarmInfoMapper alarmInfoMapper;

	@Override
	public IPage<AlarmInfo> findListPage(Page<AlarmInfo> page, Integer siteId, String startDate, String endDate, Integer type, Integer siteArea, Integer projectId) {
		// TODO Auto-generated method stub
		return alarmInfoMapper.findListPage(page, siteId, startDate, endDate, type, siteArea, projectId);
	}

}
