package com.env.web.mapper;

import com.env.web.entity.MonitoringData;
import com.env.web.vo.MonitoringDataVo;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since 2020-03-30
 */
public interface MonitoringDataMapper extends BaseMapper<MonitoringData> {

	List<MonitoringDataVo> statisticalTrenda(@Param("siteId")int siteId, @Param("startDate")String startDate, @Param("endDate")String endDate);

	Integer findId(@Param("siteId")String startDate, @Param("siteId")String endDate, @Param("siteId")int siteId, @Param("page")int page, @Param("type")int type);

	List<MonitoringDataVo> findHistory(@Param("siteId")int siteId, @Param("minId")int minId, @Param("maxId")int maxId);

	Long findCount(@Param("startDate")String startDate, @Param("endDate")String endDate, @Param("siteId")Integer siteId);

	Integer findIdA(@Param("siteId")String startDate, @Param("siteId")String endDate, @Param("siteId")int siteId, @Param("page")int page, @Param("type")int type);

}
