package com.env.web.service.impl;

import com.env.web.entity.SiteInfo;
import com.env.web.entity.UserInfo;
import com.env.web.mapper.SiteInfoMapper;
import com.env.web.mapper.UserInfoMapper;
import com.env.web.service.DictionaryTypeService;
import com.env.web.service.SiteInfoService;
import com.env.web.service.UserInfoService;
import com.env.web.vo.SiteInfoVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

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
public class SiteInfoServiceImpl extends ServiceImpl<SiteInfoMapper, SiteInfo> implements SiteInfoService {

	@Resource
	private SiteInfoMapper siteInfoMapper;
	@Resource
	private UserInfoService userInfoService;
	@Resource
	private DictionaryTypeService dictionaryTypeService;
	@Resource
	private UserInfoMapper userInfoMapper;
	
	@Override
	public Collection<SiteInfoVo> findSiteList(SiteInfo siteInfo) {
		// TODO Auto-generated method stub
		return siteInfoMapper.findSiteList(siteInfo);
	}

	@Override
	public List<String> selectCode(Integer projectId) {
		// TODO Auto-generated method stub
		return siteInfoMapper.selectCode(projectId);
	}

	@Override
	public IPage<SiteInfoVo> sitePage(Page<SiteInfo> page, SiteInfo site) {
		// TODO Auto-generated method stub
		IPage<SiteInfoVo> pageList = siteInfoMapper.sitePage(page, site);
		Map<Integer,String> userMap = userInfoService.getIdNameMap(site.getProjectId());
		Map<Integer,String> areaMap = dictionaryTypeService.getDictionaryList(site.getProjectId(),4);
		for (SiteInfoVo vo : pageList.getRecords()) {
			String managers = " ";
			//根据区域id查找所有负责人
			List<UserInfo> users = userInfoMapper.getListByArea(vo.getSiteArea());
			for (UserInfo user : users) {
				managers += userMap.get(user.getId()) + ",";
			}
			vo.setManager(managers.substring(0, managers.length() - 1));
			vo.setCreateUserName(userMap.get(vo.getCreateUser()));
			vo.setAreaName(areaMap.get(vo.getSiteArea()));
		}
		return pageList;
	}

	@Override
	public Map<String, String> getCodeNameMap(Integer projectId) {
		// TODO Auto-generated method stub
		QueryWrapper<SiteInfo> queryWrapper = new QueryWrapper<>();
		if(projectId != 0) {
			queryWrapper.eq("project_id", projectId);
		}
		queryWrapper.eq("state", 0);
		List<SiteInfo> list = siteInfoMapper.selectList(queryWrapper);
		Map<String,String> map = new HashMap<>();
		list.forEach(item -> {
			map.put(item.getAccessCode(), item.getSiteName());
		});
		return map;
	}

}
