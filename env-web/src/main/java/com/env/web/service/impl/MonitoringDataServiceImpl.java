package com.env.web.service.impl;

import com.env.web.entity.MonitoringData;
import com.env.web.mapper.MonitoringDataMapper;
import com.env.web.service.MonitoringDataService;
import com.env.web.vo.MonitoringDataVo;
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
public class MonitoringDataServiceImpl extends ServiceImpl<MonitoringDataMapper, MonitoringData> implements MonitoringDataService {
	
	@Autowired
	private MonitoringDataMapper monitoringDataMapper;
	
	@Override
	public List<MonitoringDataVo> statisticalTrenda(int siteId, String startDate, String endDate) {
		// TODO Auto-generated method stub
		return monitoringDataMapper.statisticalTrenda(siteId, startDate, endDate);
	}

	@Override
	public Integer findId(String endDate, String startDate, int siteId, int page, int type) {
		// TODO Auto-generated method stub
		return monitoringDataMapper.findId(startDate, endDate, siteId, page, type);
	}

	@Override
	public List<MonitoringDataVo> findHistory(int siteId, Integer minId, Integer maxId) {
		// TODO Auto-generated method stub
		return monitoringDataMapper.findHistory(siteId, minId, maxId);
	}

	@Override
	public Long findCount(String endDate, String startDate, Integer siteId) {
		// TODO Auto-generated method stub
		return monitoringDataMapper.findCount(startDate, endDate, siteId);
	}

}
