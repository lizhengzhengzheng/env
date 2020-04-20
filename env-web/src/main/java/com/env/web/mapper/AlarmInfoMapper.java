package com.env.web.mapper;

import com.env.web.entity.AlarmInfo;

import org.springframework.data.repository.query.Param;

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
public interface AlarmInfoMapper extends BaseMapper<AlarmInfo> {

	IPage<AlarmInfo> findListPage(Page<AlarmInfo> page, @Param("siteId")Integer siteId, @Param("startDate")String startDate, @Param("endDate")String endDate, @Param("type")Integer type, @Param("siteArea")Integer siteArea, @Param("projectId")Integer projectId);

}
