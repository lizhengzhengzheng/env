package com.env.web.mapper;

import com.env.web.entity.RoleInfo;
import com.env.web.vo.RoleInfoVo;

import io.lettuce.core.dynamic.annotation.Param;

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
public interface RoleInfoMapper extends BaseMapper<RoleInfo> {

	IPage<RoleInfoVo> roleList(Page<?> page, @Param("role")RoleInfo role);

}
