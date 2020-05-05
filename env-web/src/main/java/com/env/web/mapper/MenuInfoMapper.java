package com.env.web.mapper;

import com.env.web.entity.MenuInfo;
import com.env.web.vo.MenuTreeVo;

import io.lettuce.core.dynamic.annotation.Param;

import java.util.List;
import java.util.Set;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 后台菜单表 Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since 2020-04-06
 */
public interface MenuInfoMapper extends BaseMapper<MenuInfo> {

	List<MenuTreeVo> findMenuTree(@Param("menuIds")Set<Integer> menuIds);

}
