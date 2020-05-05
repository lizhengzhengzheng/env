package com.env.web.service;

import com.env.web.entity.MonitoringData;
import com.env.web.vo.MonitoringDataVo;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ${author}
 * @since 2020-03-30
 */
public interface MonitoringDataService extends IService<MonitoringData> {

	List<MonitoringDataVo> statisticalTrenda(int siteId, String startDate, String endDate);

	Integer findId(String endDate, String startDate, int siteId, int page, int type);

	List<MonitoringDataVo> findHistory(int siteId, Integer minId, Integer maxId);

	Long findCount(String endDate, String startDate, Integer siteId);

}
