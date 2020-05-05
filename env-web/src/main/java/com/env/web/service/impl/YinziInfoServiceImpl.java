package com.env.web.service.impl;

import com.env.web.entity.YinziInfo;
import com.env.web.mapper.YinziInfoMapper;
import com.env.web.service.YinziInfoService;
import com.env.web.vo.YinziInfoVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
public class YinziInfoServiceImpl extends ServiceImpl<YinziInfoMapper, YinziInfo> implements YinziInfoService {

	@Autowired
	private YinziInfoMapper yinziInfoMapper;
	
	@Override
	public IPage<YinziInfoVo> findListPage(Page<YinziInfo> page, YinziInfo yinziInfo) {
		// TODO Auto-generated method stub
		IPage<YinziInfoVo> pageList = yinziInfoMapper.findListPage(page, yinziInfo);
		for (YinziInfoVo vo : pageList.getRecords()) {
			vo.setLimit1(vo.getLowerLimit1() + "~" + vo.getUpperLimit1());
			vo.setLimit2(vo.getLowerLimit2() + "~" + vo.getUpperLimit2());
			vo.setLimit3(vo.getLowerLimit3() + "~" + vo.getUpperLimit3());
			vo.setLimit4(vo.getLowerLimit4() + "~" + vo.getUpperLimit4());
			vo.setLimit5(vo.getLowerLimit5() + "~" + vo.getUpperLimit5());
		}
		return pageList;
	}
	
	@Override
	public Map<Integer, String> getIdNameMap(Integer projectId) {
		// TODO Auto-generated method stub
		QueryWrapper<YinziInfo> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("state", 0);
		queryWrapper.eq("project_id", projectId);
		List<YinziInfo> list = yinziInfoMapper.selectList(queryWrapper);
		Map<Integer, String> map = new HashMap<>();
		list.forEach(item -> {
			map.put(item.getId(), item.getName());
		});
		return map;
	}

}
