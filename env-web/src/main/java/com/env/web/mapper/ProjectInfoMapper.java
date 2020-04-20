package com.env.web.mapper;

import com.env.web.entity.ProjectInfo;
import com.env.web.vo.ProjectInfoVo;

import org.apache.ibatis.annotations.Param;

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
public interface ProjectInfoMapper extends BaseMapper<ProjectInfo> {
	
	int siteAlarm(@Param("projectId")Integer projectId);

	int equipAlarm(@Param("projectId")Integer projectId);

	int checkNum(@Param("projectId")Integer projectId);

	void addProject(ProjectInfo project);

	IPage<ProjectInfoVo> findListPage(Page<ProjectInfo> page, @Param("project")ProjectInfo project);
}
