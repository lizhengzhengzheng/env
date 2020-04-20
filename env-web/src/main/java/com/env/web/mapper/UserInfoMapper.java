package com.env.web.mapper;

import com.env.web.entity.UserInfo;
import com.env.web.vo.UserInfoVo;

import io.lettuce.core.dynamic.annotation.Param;

import java.util.List;

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
public interface UserInfoMapper extends BaseMapper<UserInfo> {

	IPage<UserInfoVo> findListPage(Page<?> page, @Param("userInfo")UserInfo userInfo);

	List<UserInfo> getListByArea(Integer siteArea);

}
