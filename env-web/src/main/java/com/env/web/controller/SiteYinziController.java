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
import com.env.web.entity.SiteYinzi;
import com.env.web.entity.UserInfo;
import com.env.web.service.SiteYinziService;
import com.env.web.vo.SiteYinziVo;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2020-03-30
 */
@RestController
@RequestMapping("/siteYinzi")
public class SiteYinziController {
	
	@Autowired
	private SiteYinziService siteYinziService;
	
	/**
	 * 检测项列表
	 * @author lizheng
	 * @param page
	 * @param limit
	 * @param siteYinzi
	 * @return
	 * @return ResponseList 返回类型
	 * @throws
	 */
	@RequestMapping("/pageList")
	public ResponseList pageList(int page, int limit, SiteYinzi siteYinzi) {
		ResponseList responseList = new ResponseList();
		IPage<SiteYinziVo> list = siteYinziService.findListPage(new Page<SiteYinzi>(page,limit), siteYinzi);
		responseList.success(list);
		return responseList;
	}
	
	/**
	 * 添加检测项
	 * @author lizheng
	 * @param siteYinzi
	 * @param session
	 * @return
	 * @return ResponseApi 返回类型
	 * @throws
	 */
	@RequestMapping("/insert")
	public ResponseApi insert(SiteYinzi siteYinzi, HttpSession session) {
		UserInfo user = UserHelper.getUserInfo(session);
		ResponseApi responseApi = new ResponseApi();
		QueryWrapper<SiteYinzi> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("site_id", siteYinzi.getYinziId());
		queryWrapper.eq("state", 0);
		queryWrapper.eq("yinzi_id", siteYinzi.getYinziId());
		SiteYinzi a = siteYinziService.getOne(queryWrapper);
		if (a != null) {
			responseApi.fail("检测项已存在");
		} else {
			siteYinzi.setCreateUser(user.getId());
			siteYinziService.save(siteYinzi);
			responseApi.success();
		}
		return responseApi;
	}

	/**
	 * 编辑检测项
	 * @author lizheng
	 * @param siteYinzi
	 * @param session
	 * @return
	 * @return ResponseApi 返回类型
	 * @throws
	 */
	@RequestMapping("/update")
	public ResponseApi update(SiteYinzi siteYinzi) {
		ResponseApi responseApi = new ResponseApi();
		siteYinziService.updateById(siteYinzi);
		responseApi.success();
		return responseApi;
	}
	
	/**
	 * 删除检测项
	 * @author lizheng
	 * @param siteYinzi
	 * @return
	 * @return ResponseApi 返回类型
	 * @throws
	 */
	@RequestMapping("/delete")
	public ResponseApi delete(SiteYinzi siteYinzi) {
		ResponseApi api = new ResponseApi();
		siteYinzi.setState(1);
		siteYinziService.updateById(siteYinzi);
		api.success();
		return api;
	}
	

}

