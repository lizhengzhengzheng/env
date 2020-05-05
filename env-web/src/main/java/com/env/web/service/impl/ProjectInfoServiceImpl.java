package com.env.web.service.impl;

import com.env.web.entity.ProjectInfo;
import com.env.web.entity.SiteInfo;
import com.env.web.entity.UserInfo;
import com.env.web.mapper.ProjectInfoMapper;
import com.env.web.mapper.SiteInfoMapper;
import com.env.web.mapper.UserInfoMapper;
import com.env.web.service.ProjectInfoService;
import com.env.web.service.UserInfoService;
import com.env.web.vo.ConsoleVo;
import com.env.web.vo.ProjectInfoVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.Map;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2020-03-30
 */
@Service
public class ProjectInfoServiceImpl extends ServiceImpl<ProjectInfoMapper, ProjectInfo> implements ProjectInfoService {

	@Resource
	public ProjectInfoMapper projectInfoMapper;
	
	@Resource
	public SiteInfoMapper siteInfoMapper;
	
	@Resource
	public UserInfoService userInfoService;
	
	@Resource
	public UserInfoMapper userInfoMapper;

	@Override
	public ConsoleVo console(Integer projectId) {
		ConsoleVo con = new ConsoleVo();
		//总站点数
		QueryWrapper<SiteInfo> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("project_id", projectId);
		queryWrapper.eq("state", 0);
		int siteNum = siteInfoMapper.selectCount(queryWrapper);
		con.setSiteNum(siteNum);
		//站点报警
		int siteAlarm = projectInfoMapper.siteAlarm(projectId);
		con.setSiteAlarm(siteAlarm);
		//设备报警
		int equipAlarm = projectInfoMapper.equipAlarm(projectId);
		con.setEquipAlarm(equipAlarm);
		//总检测次数
		int checkNum = projectInfoMapper.checkNum(projectId);
		con.setCheckNum(checkNum);
		return con;
	}

	@Override
	public IPage<ProjectInfoVo> findListPage(Page<ProjectInfo> page, ProjectInfo project) {
		// TODO Auto-generated method stub
		IPage<ProjectInfoVo> pageList = projectInfoMapper.findListPage(page, project);
		Map<Integer,String> userMap = userInfoService.getIdNameMap(0);
		for (ProjectInfoVo vo : pageList.getRecords()) {
			String[] userIds = vo.getUserId().split(",");
			String userNames = " ";
			for (String userId : userIds) {
				if(!userId.equals("")) {
					userNames += userMap.get(Integer.valueOf(userId)) + ",";
				}
			}
			vo.setUserNames(userNames.substring(0,userNames.length() - 1));
		}
		return pageList;
	}

	@Transactional
	@Override
	public void insert(ProjectInfo project) {
		// TODO Auto-generated method stub
		//保存
		projectInfoMapper.addProject(project);
		//为负责人配置账户
		String[] userIds = project.getUserId().split(",");
		for (String id : userIds) {
			if(id.equals("")) {
				continue;
			}
			UserInfo user = userInfoMapper.selectById(id);
			user.setCategory(2);
			user.setProjectId(project.getId());
			user.setSiteArea("");
			user.setBackstageRoleList("");
			userInfoMapper.updateById(user);
		}
	}

	@Transactional
	@Override
	public void update(ProjectInfo project) {
		// TODO Auto-generated method stub
		ProjectInfo origin = projectInfoMapper.selectById(project.getId());
		//负责人有变动，设置之前的负责人category为3，为新负责人配置账户
		if(!origin.getUserId().equals(project.getUserId())) {
			String[] origins = origin.getUserId().split(",");
			for (String id : origins) {
				if(id.equals("0") || id.equals("")) {
					continue;
				}
				UserInfo user = userInfoMapper.selectById(id);
				user.setCategory(3);
				userInfoMapper.updateById(user);
			}
			String[] userIds = project.getUserId().split(",");
			for (String id : userIds) {
				if(id.equals("")) {
					continue;
				}
				UserInfo user = userInfoMapper.selectById(id);
				user.setCategory(2);
				user.setProjectId(project.getId());
				user.setSiteArea("");
				user.setBackstageRoleList("");
				userInfoMapper.updateById(user);
			}
		}
		projectInfoMapper.updateById(project);
		
	}

	@Transactional
	@Override
	public void delete(ProjectInfo project) {
		// TODO Auto-generated method stub
		//项目下用户设置为禁用
		QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("project_id", project.getId());
		queryWrapper.eq("state", 0);
		UserInfo user = new UserInfo();
		user.setState(2);
		userInfoMapper.update(user, queryWrapper);
		//逻辑删除项目
		project.setState(1);
		projectInfoMapper.updateById(project);
	}

}
