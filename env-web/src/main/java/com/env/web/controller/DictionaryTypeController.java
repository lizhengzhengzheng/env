package com.env.web.controller;

import java.util.Map;
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
import com.env.web.entity.DictionaryType;
import com.env.web.entity.UserInfo;
import com.env.web.service.DictionaryTypeService;
import com.env.web.service.RoleInfoService;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2020-03-30
 */
@RestController
@RequestMapping("/dictionaryType")
public class DictionaryTypeController {
	
	@Autowired
	private DictionaryTypeService dictionaryTypeService;
	@Autowired
	private RoleInfoService roleInfoService;
	
	@RequestMapping("/getDictionaryList")
	public ResponseApi getDictionaryList(HttpSession session, int type) {
		ResponseApi api = new ResponseApi();
		UserInfo user = UserHelper.getUserInfo(session);
		Map<Integer,String> map = dictionaryTypeService.getDictionaryList(user.getProjectId(), type);
		api.success(map);
		return api;
	}
	
	/**
	 * 字典项列表
	 * @author lizheng
	 * @param @param page
	 * @param @param limit
	 * @param @param dictionaryType
	 * @param @param session
	 * @param @return
	 * @return ResponseList 返回类型
	 * @throws
	 */
	@RequestMapping("/pageList")
	public ResponseList pageList(int page, int limit, DictionaryType dictionaryType, HttpSession session) {
		UserInfo user = UserHelper.getUserInfo(session);
		dictionaryType.setProjectId(user.getProjectId());
		ResponseList responseList = new ResponseList();
		IPage<DictionaryType> list = dictionaryTypeService.findListPage(new Page<DictionaryType>(page,limit), dictionaryType);
		responseList.success(list);
		return responseList;
	}
	
	/**
	 * 添加字典项
	 * @author lizheng
	 * @param @param dictionaryType
	 * @param @param session
	 * @param @return
	 * @return ResponseApi 返回类型
	 * @throws
	 */
	@RequestMapping("/insert")
	public ResponseApi insert(DictionaryType dictionaryType, HttpSession session) {
		ResponseApi responseApi = new ResponseApi();
		if(!roleInfoService.isHave(session, 47)) {
			responseApi.fail("无权限");
			return responseApi;
		}
		UserInfo user = UserHelper.getUserInfo(session);
		QueryWrapper<DictionaryType> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("project_id", user.getProjectId());
		queryWrapper.eq("state", 0);
		queryWrapper.eq("type", dictionaryType.getType());
		queryWrapper.eq("alarm_num", dictionaryType.getAlarmNum());
		DictionaryType a = dictionaryTypeService.getOne(queryWrapper);
		if (a != null) {
			// 因子编码已存在
			responseApi.fail("因子编码已存在");
		} else {
			dictionaryType.setProjectId(user.getProjectId());
			dictionaryType.setCreateUser(user.getId());
			dictionaryTypeService.save(dictionaryType);
			responseApi.success();
		}
		return responseApi;
	}

	/**
	 * 编辑字典项
	 * @author lizheng
	 * @param @param dictionaryType
	 * @param @param session
	 * @param @return
	 * @return ResponseApi 返回类型
	 * @throws
	 */
	@RequestMapping("/update")
	public ResponseApi update(DictionaryType dictionaryType, HttpSession session) {
		ResponseApi responseApi = new ResponseApi();
		if(!roleInfoService.isHave(session, 48)) {
			responseApi.fail("无权限");
			return responseApi;
		}
		DictionaryType a = dictionaryTypeService.getById(dictionaryType.getId());
		UserInfo user = UserHelper.getUserInfo(session);
		if(!a.getAlarmNum().equals(dictionaryType.getAlarmNum())) {
			QueryWrapper<DictionaryType> queryWrapper = new QueryWrapper<>();
			queryWrapper.eq("project_id", user.getProjectId());
			queryWrapper.eq("state", 0);
			queryWrapper.eq("type", dictionaryType.getType());
			queryWrapper.eq("yinzi_num", dictionaryType.getAlarmNum());
			DictionaryType b = dictionaryTypeService.getOne(queryWrapper);
			if (b != null) {
				// 因子编码已存在
				responseApi.fail("编码已存在");
			} else {
				dictionaryTypeService.updateById(dictionaryType);
				responseApi.success();
			}
		}else {
			dictionaryTypeService.updateById(dictionaryType);
			responseApi.success();
		}
		return responseApi;
	}
	
	/**
	 * 删除字典项
	 * @author lizheng
	 * @param @param dictionaryType
	 * @param @return
	 * @return ResponseApi 返回类型
	 * @throws
	 */
	@RequestMapping("/delete")
	public ResponseApi delete(DictionaryType dictionaryType, HttpSession session) {
		ResponseApi api = new ResponseApi();
		if(!roleInfoService.isHave(session, 49)) {
			api.fail("无权限");
			return api;
		}
		dictionaryType.setState(1);
		dictionaryTypeService.updateById(dictionaryType);
		api.success();
		return api;
	}
}

