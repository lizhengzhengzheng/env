package com.env.web.controller;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.env.web.common.ResponseApi;
import com.env.web.common.ResponseList;
import com.env.web.common.UserHelper;
import com.env.web.entity.ProjectInfo;
import com.env.web.entity.UserInfo;
import com.env.web.service.ProjectInfoService;
import com.env.web.vo.ProjectInfoVo;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2020-03-30
 */
@RestController
@RequestMapping("/project")
public class ProjectInfoController {
	
	@Autowired
	private ProjectInfoService projectInfoService;
	
	/**
	 * 获取项目列表
	 * @author lizheng
	 * @param @param page
	 * @param @param limit
	 * @param @param project
	 * @param @return
	 * @return ResponseList 返回类型
	 * @throws
	 */
	@RequestMapping("/pageList")
	public ResponseList pageList(int page, int limit, ProjectInfo project) {
		ResponseList responseList = new ResponseList();
		IPage<ProjectInfoVo> list = projectInfoService.findListPage(new Page<ProjectInfo>(page,limit), project);
		responseList.success(list);
		return responseList;
	}
	
	/**
	 * 新建项目
	 * @author lizheng
	 * @param @param project
	 * @param @param session
	 * @param @return
	 * @return ResponseApi 返回类型
	 * @throws
	 */
	@RequestMapping("/insert")
	public ResponseApi insert(ProjectInfo project, HttpSession session) {
		ResponseApi responseApi = new ResponseApi();
		responseApi.success();
		UserInfo user = UserHelper.getUserInfo(session);
		project.setCreateUser(user.getId());
		try {
			projectInfoService.insert(project);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			responseApi.fail("系统异常");
		}
		return responseApi;
	}
	
	/**
	 * 编辑项目信息
	 * @author lizheng
	 * @param @param project
	 * @param @return
	 * @return ResponseApi 返回类型
	 * @throws
	 */
	@RequestMapping("/update")
	public ResponseApi update(ProjectInfo project) {
		ResponseApi responseApi = new ResponseApi();
		responseApi.success();
		try {
			projectInfoService.update(project);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			responseApi.fail("系统异常");
		}
		return responseApi;
	}
	
	/**
	 * 删除项目
	 * @author lizheng
	 * @param @param project
	 * @param @return
	 * @return ResponseApi 返回类型
	 * @throws
	 */
	@RequestMapping("/delete")
	public ResponseApi delete(ProjectInfo project) {
		ResponseApi api = new ResponseApi();
		project.setState(1);
		projectInfoService.delete(project);
		api.success();
		return api;
	}
}

