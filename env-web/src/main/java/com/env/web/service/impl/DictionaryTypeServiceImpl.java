package com.env.web.service.impl;

import com.env.web.entity.DictionaryType;
import com.env.web.mapper.DictionaryTypeMapper;
import com.env.web.service.DictionaryTypeService;
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
public class DictionaryTypeServiceImpl extends ServiceImpl<DictionaryTypeMapper, DictionaryType> implements DictionaryTypeService {
	
	@Autowired
	private DictionaryTypeMapper dictionaryTypeMapper;

	@Override
	public Map<Integer, String> getDictionaryList(Integer projectId, int type) {
		// TODO Auto-generated method stub
		QueryWrapper<DictionaryType> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("project_id", projectId);
		queryWrapper.eq("type", type);
		queryWrapper.eq("state", 0);
		List<DictionaryType> list = dictionaryTypeMapper.selectList(queryWrapper);
		Map<Integer, String> map = new HashMap<>();
		list.forEach(item -> {
			map.put(item.getId(), item.getName());
		});
		return map;
	}

	@Override
	public IPage<DictionaryType> findListPage(Page<DictionaryType> page, DictionaryType dictionaryType) {
		// TODO Auto-generated method stub
		return dictionaryTypeMapper.findListPage(page, dictionaryType);
	}

}
