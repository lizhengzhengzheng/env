package com.env.web.mapper;

import com.env.web.entity.DictionaryType;

import java.util.Map;

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
public interface DictionaryTypeMapper extends BaseMapper<DictionaryType> {

	Map<Integer, String> getDictionaryList(@Param("projectId")Integer projectId, @Param("type")int type);

	IPage<DictionaryType> findListPage(Page<DictionaryType> page, DictionaryType dictionaryType);

}
