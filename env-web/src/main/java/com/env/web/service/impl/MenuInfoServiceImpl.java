package com.env.web.service.impl;

import com.env.web.entity.MenuInfo;
import com.env.web.mapper.MenuInfoMapper;
import com.env.web.service.MenuInfoService;
import com.env.web.vo.MenuTreeVo;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 后台菜单表 服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2020-04-06
 */
@Service
public class MenuInfoServiceImpl extends ServiceImpl<MenuInfoMapper, MenuInfo> implements MenuInfoService {
	
	@Autowired
	private MenuInfoMapper menuInfoMapper;
	
	@Override
	public List<MenuTreeVo> findMenuTree() {
		// TODO Auto-generated method stub
		return menuInfoMapper.findMenuTree();
	}

}
