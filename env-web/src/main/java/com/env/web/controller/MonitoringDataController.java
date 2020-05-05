package com.env.web.controller;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.env.web.common.ResponseApi;
import com.env.web.common.ResponseList;
import com.env.web.common.UserHelper;
import com.env.web.entity.SiteYinzi;
import com.env.web.entity.UserInfo;
import com.env.web.entity.YinziInfo;
import com.env.web.service.MonitoringDataService;
import com.env.web.service.SiteYinziService;
import com.env.web.service.YinziInfoService;
import com.env.web.util.DynamicFactory;
import com.env.web.util.LevelAppraisal;
import com.env.web.util.TableHeadElement;
import com.env.web.vo.HistoryDataVo;
import com.env.web.vo.MapHeaderVo;
import com.env.web.vo.MonitorVo;
import com.env.web.vo.MonitoringDataVo;
import com.env.web.vo.MultipointVo;
import com.env.web.vo.TrendVo;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2020-03-30
 */
@RestController
@RequestMapping("/monitoringData")
public class MonitoringDataController {
	
	@Autowired
	private YinziInfoService yinziInfoService;
	@Autowired
	private MonitoringDataService monitoringDataService;
	@Autowired
	private SiteYinziService siteYinziService;
	
	/**
	 * @throws ParseException 
	 * 查询数据列表（用于趋势统计）
	 * @author: 李正
	 * @param monitoringData      
	 * @return: void      
	 * @throws
	 */
	@RequestMapping("/statistical-trenda")
	public ResponseApi statisticalTrenda(Integer siteId, String startDate, String endDate, HttpSession session) throws ParseException{
		siteId = siteId == null? 0:siteId;
		Map<String,YinziInfo> map = new HashMap<String,YinziInfo>();
		UserInfo user = UserHelper.getUserInfo(session);
		QueryWrapper<YinziInfo> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("project_id", user.getProjectId());
		queryWrapper.eq("state", 0);
	    List<YinziInfo> yinzis = yinziInfoService.list(queryWrapper);
	    for(YinziInfo yinzi : yinzis)
	    	map.put(yinzi.getYinziNum(), yinzi);
		TrendVo trendVo = new TrendVo();
		ArrayList<MonitorVo> vos = new ArrayList<MonitorVo> ();
		ResponseApi api = new ResponseApi();
		if(siteId == 0) {
			trendVo.setIsNull(0);
			trendVo.setDate(null);
			trendVo.setSiteName(null);
			trendVo.setYinzi(null);
			MonitorVo monitorVo = new MonitorVo();
			monitorVo.setName(null);
			monitorVo.setType("line");
			monitorVo.setData(null);
			vos.add(monitorVo);
			trendVo.setData(vos);
			api.setData(trendVo);
			api.success();
			return api;
		}
		if(endDate != null && !endDate.equals("")) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();  
        c.setTime(df.parse(endDate));  
        c.add(Calendar.DAY_OF_MONTH, 1);   
        endDate = df.format(c.getTime());
        }
		List<MonitoringDataVo> list = monitoringDataService.statisticalTrenda(siteId, startDate, endDate);
		trendVo.setIsNull(list.size() == 0? 0:1);
		if(list.size() != 0) {
			ArrayList<String> date = new ArrayList<String> ();
			ArrayList<String> yinzi = new ArrayList<String> ();
			for(int i = 0; i < list.size()-1; i++) {
				if(list.get(i).getCreateAt().compareTo(list.get(i+1).getCreateAt()) != 0)
				date.add(DateTimeFormatter.ofPattern("yyyy-MM-dd HH").format(list.get(i).getCreateAt()));
			}
			date.add(DateTimeFormatter.ofPattern("yyyy-MM-dd HH").format(list.get(list.size()-1).getCreateAt()));
			bgm:for(int i = 0; i < list.size()-1; i++) {
				yinzi.add(list.get(i).getYinziName());
				for(int j = 0; j<yinzi.size(); j++) {
					if(yinzi.get(j).equals(list.get(i+1).getYinziName()))
						break bgm;
				}
			}
			List<String> a = new ArrayList<>();
			List<MapHeaderVo> b = new ArrayList<>();
			int h = 0,k = 0,l = 0,m = 0,n = 0;
			trendVo.setSiteName(list.get(0).getSiteName());
			trendVo.setDate(date);
			trendVo.setYinzi(yinzi);
			for(int i = 0; i<yinzi.size(); i++) {
				MonitorVo monitorVo = new MonitorVo();
				monitorVo.setName(yinzi.get(i));
				monitorVo.setType("line");
				ArrayList<String> value = new ArrayList<String> ();
				for(int j = 0; j < list.size(); j++) {
					if(list.get(j).getYinziName().equals(monitorVo.getName())) {
						value.add(String.valueOf(list.get(j).getYinziValue()));
						String level = LevelAppraisal.Level(list.get(j),map.get(list.get(j).getYinziNum()));
						if(level.equals("Ⅰ类")) h++;
						else if(level.equals("Ⅱ类")) k++;
						else if(level.equals("Ⅲ类")) l++;
						else if(level.equals("Ⅳ类")) m++;
						else if(level.equals("Ⅴ类")) n++;
					}
				}
				monitorVo.setData(value);
				vos.add(monitorVo);
			}
			if(h>0) {
				MapHeaderVo c = new MapHeaderVo();
				c.setName("Ⅰ类 " + String.valueOf(h) + "个");
				c.setValue(String.valueOf(h));
				b.add(c);
				a.add("Ⅰ类 " + String.valueOf(h) + "个");
			}
			if(k>0) {
				MapHeaderVo d = new MapHeaderVo();
				d.setName("Ⅱ类 " + String.valueOf(k) + "个");
				d.setValue(String.valueOf(k));
				a.add("Ⅱ类 " + String.valueOf(k) + "个");
				b.add(d);
			}
			if(l>0) {
				MapHeaderVo e = new MapHeaderVo();
				e.setName("Ⅲ类 " + String.valueOf(l) + "个");
				e.setValue(String.valueOf(l));
				b.add(e);
				a.add("Ⅲ类 " + String.valueOf(l) + "个");
			}
			if(m>0) {
				MapHeaderVo f = new MapHeaderVo();
				f.setName("Ⅳ类 " + String.valueOf(m) + "个");
				f.setValue(String.valueOf(m));
				b.add(f);
				a.add("Ⅳ类 " + String.valueOf(m) + "个");
			}
			if(n>0) {
				MapHeaderVo g = new MapHeaderVo();
				g.setName("Ⅴ类 " + String.valueOf(n) + "个");
				g.setValue(String.valueOf(n));
				b.add(g);
				a.add("Ⅴ类 " + String.valueOf(n) + "个");
			}
			trendVo.setData(vos);
			trendVo.setCakeData(b);
			trendVo.setTransverseData(a);
		}else {
			trendVo.setDate(null);
			trendVo.setSiteName(null);
			trendVo.setYinzi(null);
			MonitorVo monitorVo = new MonitorVo();
			monitorVo.setName(null);
			monitorVo.setType("line");
			monitorVo.setData(null);
			vos.add(monitorVo);
			trendVo.setData(vos);
		}
		api.setData(trendVo);
		api.success();
		return api;
	}
	
	/**
	 * 历史数据-表头
	 * @author: 李正
	 * @param session
	 * @return
	 * @throws Exception      
	 * @return: ResponseApi      
	 * @throws
	 */
	@RequestMapping(value = "/historyHeader", method = RequestMethod.POST)
	public ResponseApi historyHeader(Integer siteId) throws Exception {
		siteId = siteId == null? 0:siteId;
		ResponseApi api = new ResponseApi();
		QueryWrapper<SiteYinzi> siteYinziWrapper = new QueryWrapper<>();
		siteYinziWrapper.eq("site_id", siteId);
		siteYinziWrapper.ne("state", 1);
	    List<SiteYinzi> yinzis = siteYinziService.list(siteYinziWrapper);
	    List<Integer> yinziIds = yinzis.stream().map(SiteYinzi::getYinziId).collect(Collectors.toList());
	    yinziIds.add(0);
	    QueryWrapper<YinziInfo> yinziWrapper = new QueryWrapper<>();
	    yinziWrapper.in("id", yinziIds);
	    List<YinziInfo> list = yinziInfoService.list(yinziWrapper);
		List<Object> header = new ArrayList<>();
		TableHeadElement b = new TableHeadElement("siteName","站点名称"),
				c = new TableHeadElement("addTime","监测时间");
		header.add(b);header.add(c);
		for(int i=0; i<list.size(); i++) {
			TableHeadElement f = new TableHeadElement(list.get(i).getYinziNum(),list.get(i).getName()+list.get(i).getUnit());
			header.add(f);
		}
		api.setData(header);
		api.success();
		return api;
	}
	
	/**
	 * @throws Exception 
	 * 历史数据-数据
	 * @author: 李正
	 * @param site
	 * @param page
	 * @return      
	 * @return: ResponseList      
	 * @throws
	 */
	@RequestMapping(value = "/historyData", method = RequestMethod.POST)
	public ResponseList historyData(String endDate, String startDate, Integer siteId, int page, int limit) throws Exception {
		ResponseList responseList = new ResponseList();
		List<Object> vos = new ArrayList<>();
		if(endDate != null && !endDate.equals("")) {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	        Calendar c = Calendar.getInstance();  
	        c.setTime(df.parse(endDate));  
	        c.add(Calendar.DAY_OF_MONTH, 1);   
	        endDate = df.format(c.getTime());
		}
		//取出前limit次的最大id和最小id
		siteId = siteId == null? 0:siteId;
		Integer maxId = monitoringDataService.findId(endDate, startDate, siteId, (page - 1) * limit, 0);
		Integer minId = monitoringDataService.findId(endDate, startDate, siteId, page * limit - 1, 1);
		Long count = monitoringDataService.findCount(endDate, startDate, siteId);
		maxId = maxId == null? 0:maxId;
		minId = minId == null? 0:minId;
		count = count == null? 0:count;
		//id范围查询数据
		List<MonitoringDataVo> monitoringDatas = monitoringDataService.findHistory(siteId, minId, maxId);
		//处理成HistoryDataVo形式
		List<HistoryDataVo> list = new ArrayList<>();
		if(monitoringDatas.size() > 0) {
			HistoryDataVo vo = new HistoryDataVo();
			vo.setCreateAt(monitoringDatas.get(0).getCreateAt());
			vo.setSiteName(monitoringDatas.get(0).getSiteName());
			vo.getData().add(monitoringDatas.get(0));
			list.add(vo);
		}
		for(int i = 1; i < monitoringDatas.size(); i++) {
			if(monitoringDatas.get(i).getSerialNum().equals(monitoringDatas.get(i - 1).getSerialNum())) {
				list.get(list.size() - 1).getData().add(monitoringDatas.get(i));
			}else {
				HistoryDataVo vo = new HistoryDataVo();
				vo.setCreateAt(monitoringDatas.get(i).getCreateAt());
				vo.setSiteName(monitoringDatas.get(i).getSiteName());
				vo.getData().add(monitoringDatas.get(i));
				list.add(vo);
			}
		}
        DecimalFormat df=new DecimalFormat("#.########");
        for (HistoryDataVo item : list) {
        	List<String> name = new ArrayList<>();
			List<String> value = new ArrayList<>();
			name.add("siteName");
			value.add(item.getSiteName());		
			name.add("addTime");
			value.add(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(item.getCreateAt()));
			for(int j = 0; j<item.getData().size(); j++) {
				name.add(item.getData().get(j).getYinziNum());
				if(item.getData().get(j).getYinziValue() == null || item.getData().get(j).getYinziValue() == 0.000)
					value.add("-");
				else 
					value.add(df.format(item.getData().get(j).getYinziValue()));
			}
			Object object = DynamicFactory.dynamicClass(new MultipointVo(), name, value);
			vos.add(object);
		}
		responseList.success(vos);
		responseList.setCount(count);
		return responseList;
	}
	
}

