package com.env.web.controller;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.env.web.common.ResponseApi;
import com.env.web.common.ResponseList;
import com.env.web.common.UserHelper;
import com.env.web.entity.UserInfo;
import com.env.web.entity.YinziInfo;
import com.env.web.service.RoleInfoService;
import com.env.web.service.YinziInfoService;
import com.env.web.vo.YinziInfoVo;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2020-03-30
 */
@RestController
@RequestMapping("/yinzi")
public class YinziInfoController {
	
	@Autowired
	private YinziInfoService yinziInfoService;
	@Autowired
	private RoleInfoService roleInfoService;
	
	/**
	 * 因子列表
	 * @author lizheng
	 * @param @param page
	 * @param @param limit
	 * @param @param yinziInfo
	 * @param @param session
	 * @param @return
	 * @return ResponseList 返回类型
	 * @throws
	 */
	@RequestMapping("/pageList")
	public ResponseList pageList(int page, int limit, YinziInfo yinziInfo, HttpSession session) {
		UserInfo user = UserHelper.getUserInfo(session);
		yinziInfo.setProjectId(user.getProjectId());
		ResponseList responseList = new ResponseList();
		IPage<YinziInfoVo> list = yinziInfoService.findListPage(new Page<YinziInfo>(page,limit), yinziInfo);
		responseList.success(list);
		return responseList;
	}
	
	/**
	 * 添加因子
	 * @author lizheng
	 * @param @param yinziInfo
	 * @param @param session
	 * @param @return
	 * @return ResponseApi 返回类型
	 * @throws
	 */
	@RequestMapping("/insert")
	public ResponseApi insert(YinziInfo yinziInfo, HttpSession session) {
		ResponseApi responseApi = new ResponseApi();
		if(!roleInfoService.isHave(session, 43)) {
			responseApi.fail("无权限");
			return responseApi;
		}
		UserInfo user = UserHelper.getUserInfo(session);
		QueryWrapper<YinziInfo> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("project_id", user.getProjectId());
		queryWrapper.eq("state", 0);
		queryWrapper.eq("yinzi_num", yinziInfo.getYinziNum());
		YinziInfo a = yinziInfoService.getOne(queryWrapper);
		if (a != null) {
			// 因子编码已存在
			responseApi.fail("因子编码已存在");
		} else {
			yinziInfo.setProjectId(user.getProjectId());
			yinziInfo.setCreateUser(user.getId());
			yinziInfoService.save(yinziInfo);
			responseApi.success();
		}
		return responseApi;
	}

	/**
	 * 编辑因子信息
	 * @author lizheng
	 * @param @param yinziInfo
	 * @param @param session
	 * @param @return
	 * @return ResponseApi 返回类型
	 * @throws
	 */
	@RequestMapping("/update")
	public ResponseApi update(YinziInfo yinziInfo, HttpSession session) {
		ResponseApi responseApi = new ResponseApi();
		if(!roleInfoService.isHave(session, 44)) {
			responseApi.fail("无权限");
			return responseApi;
		}
		YinziInfo a = yinziInfoService.getById(yinziInfo.getId());
		UserInfo user = UserHelper.getUserInfo(session);
		if(!a.getYinziNum().equals(yinziInfo.getYinziNum())) {
			QueryWrapper<YinziInfo> queryWrapper = new QueryWrapper<>();
			queryWrapper.eq("project_id", user.getProjectId());
			queryWrapper.eq("state", 0);
			queryWrapper.eq("yinzi_num", yinziInfo.getYinziNum());
			YinziInfo b = yinziInfoService.getOne(queryWrapper);
			if (b != null) {
				// 因子编码已存在
				responseApi.fail("因子编码已存在");
			} else {
				yinziInfoService.updateById(yinziInfo);
				responseApi.success();
			}
		}else {
			yinziInfoService.updateById(yinziInfo);
			responseApi.success();
		}
		return responseApi;
	}
	
	/**
	 * 删除因子信息
	 * @author lizheng
	 * @param @param yinziInfo
	 * @param @return
	 * @return ResponseApi 返回类型
	 * @throws
	 */
	@RequestMapping("/delete")
	public ResponseApi delete(YinziInfo yinziInfo, HttpSession session) {
		ResponseApi api = new ResponseApi();
		if(!roleInfoService.isHave(session, 45)) {
			api.fail("无权限");
			return api;
		}
		yinziInfo.setState(1);
		yinziInfoService.updateById(yinziInfo);
		api.success();
		return api;
	}
	
	/**
	 * 选择因子下拉框
	 * @author lizheng
	 * @param session
	 * @return
	 * @return ResponseApi 返回类型
	 * @throws
	 */
	@RequestMapping("/idNameMap")
	public ResponseApi userList(HttpSession session) {
		ResponseApi responseApi = new ResponseApi();
		UserInfo user = UserHelper.getUserInfo(session);
		responseApi.success(yinziInfoService.getIdNameMap(user.getProjectId()));
		return responseApi;
	}
}

