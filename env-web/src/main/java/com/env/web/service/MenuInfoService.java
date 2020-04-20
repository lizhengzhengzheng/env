package com.env.web.service;

import com.env.web.entity.MenuInfo;
import com.env.web.vo.MenuTreeVo;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 后台菜单表 服务类
 * </p>
 *
 * @author ${author}
 * @since 2020-04-06
 */
public interface MenuInfoService extends IService<MenuInfo> {

	List<MenuTreeVo> findMenuTree();

}
