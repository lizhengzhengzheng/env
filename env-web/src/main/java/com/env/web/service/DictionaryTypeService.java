package com.env.web.service;

import com.env.web.entity.DictionaryType;

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
public interface DictionaryTypeService extends IService<DictionaryType> {

	Map<Integer, String> getDictionaryList(Integer projectId, int type);

	IPage<DictionaryType> findListPage(Page<DictionaryType> page, DictionaryType dictionaryType);

}
