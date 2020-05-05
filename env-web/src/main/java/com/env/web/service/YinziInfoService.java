package com.env.web.service;

import com.env.web.entity.YinziInfo;
import com.env.web.vo.YinziInfoVo;

import java.util.Map;

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
public interface YinziInfoService extends IService<YinziInfo> {

	IPage<YinziInfoVo> findListPage(Page<YinziInfo> page, YinziInfo yinziInfo);
	
	Map<Integer, String> getIdNameMap(Integer projectId);

}
