package com.env.web.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.env.web.common.ResponseApi;
import com.env.web.common.ResponseList;
import com.env.web.common.UserHelper;
import com.env.web.entity.AlarmInfo;
import com.env.web.entity.UserInfo;
import com.env.web.service.AlarmInfoService;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2020-03-30
 */
@RestController
@RequestMapping("/alarm")
public class AlarmInfoController {
	
	@Autowired
	private AlarmInfoService alarmInfoService;
	
	/**
	 * 报警信息列表
	 * @author lizheng
	 * @param @param page
	 * @param @param limit
	 * @param @param siteId
	 * @param @param startDate
	 * @param @param endDate
	 * @param @return
	 * @param @throws ParseException
	 * @return ResponseList 返回类型
	 * @throws
	 */
	@RequestMapping("/selectAlarmInfoList")
	public ResponseList userList(int page, int limit, Integer siteId, Integer siteArea, String startDate, String endDate, Integer type, HttpSession session) throws ParseException {
		siteId = siteId == null? 0:siteId;
		siteArea = siteArea == null? 0:siteArea;
		if(endDate != null && !endDate.equals("")) {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	        Calendar c = Calendar.getInstance();  
	        c.setTime(df.parse(endDate));  
	        c.add(Calendar.DAY_OF_MONTH, 1);   
	        endDate = df.format(c.getTime());
        }
		UserInfo user = UserHelper.getUserInfo(session);
		ResponseList responseList = new ResponseList();
		IPage<AlarmInfo> list = alarmInfoService.findListPage(new Page<AlarmInfo>(page,limit), siteId, startDate, endDate, type, siteArea, user.getProjectId());
		responseList.success(list);
		return responseList;
	}
	
	/**
	 * 完成
	 * @author lizheng
	 * @param @param alarm
	 * @param @return
	 * @return ResponseApi 返回类型
	 * @throws
	 */
	@RequestMapping("/complete")
	public ResponseApi complete(AlarmInfo alarm) {
		ResponseApi responseApi = new ResponseApi();
		alarm.setState(2);
		alarmInfoService.updateById(alarm);
		responseApi.success();
		return responseApi;
	}
	
}

