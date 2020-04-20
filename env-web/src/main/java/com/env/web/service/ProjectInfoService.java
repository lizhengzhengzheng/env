package com.env.web.service;

import com.env.web.entity.ProjectInfo;
import com.env.web.vo.ConsoleVo;
import com.env.web.vo.ProjectInfoVo;
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
public interface ProjectInfoService extends IService<ProjectInfo> {

	ConsoleVo console(Integer projectId);

	IPage<ProjectInfoVo> findListPage(Page<ProjectInfo> page, ProjectInfo project);

	void insert(ProjectInfo project);

	void update(ProjectInfo project);

	void delete(ProjectInfo project);

}
