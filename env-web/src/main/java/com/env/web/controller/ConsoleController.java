package com.env.web.controller;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.env.web.common.ResponseApi;
import com.env.web.common.ResponseList;
import com.env.web.common.UserHelper;
import com.env.web.entity.SiteInfo;
import com.env.web.entity.UserInfo;
import com.env.web.entity.YinziInfo;
import com.env.web.service.ProjectInfoService;
import com.env.web.service.SiteInfoService;
import com.env.web.service.YinziInfoService;
import com.env.web.util.DynamicFactory;
import com.env.web.util.LevelAppraisal;
import com.env.web.util.RedisUtil;
import com.env.web.util.TableHeadElement;
import com.env.web.vo.ConsoleVo;
import com.env.web.vo.LevelVo;
import com.env.web.vo.MapHeaderVo;
import com.env.web.vo.MonitoringDataVo;
import com.env.web.vo.MultipointVo;
import com.env.web.vo.SiteInfoVo;

@RestController
@RequestMapping("/console")
public class ConsoleController {
	
	private Log log = LogFactory.getLog(this.getClass());
	
	@Resource
	private SiteInfoService siteInfoService;
	@Resource
	private YinziInfoService yinziInfoService;
	@Resource
	private ProjectInfoService projectInfoService;
	@Resource
	private RedisUtil redisUtil;
	
	/**
	 * 数据概览  项目简介
	 * @author: 李正
	 * @return      
	 * @return: ResponseApi      
	 * @throws
	 */
	@RequestMapping("/console")
	public ResponseApi console(HttpSession session) {
		ResponseApi api = new ResponseApi();
		UserInfo user = UserHelper.getUserInfo(session);
		ConsoleVo con = projectInfoService.console(user.getProjectId());
		api.success(con);
		return api;
	}
	
	/**
	 * 控制台-水质级别
	 * @author: 李正
	 * @return      
	 * @return: ResponseApi      
	 * @throws
	 */
	@RequestMapping("/waterLevel")
	public ResponseApi waterLevel(HttpSession session) {
		ResponseApi api = new ResponseApi();
		UserInfo user = UserHelper.getUserInfo(session);
		List<String> a = new ArrayList<>();
		List<MapHeaderVo> b = new ArrayList<>();
		Collection<YinziInfo> yinzi = yinziInfoService.listByMap(Map.of("project_id",user.getProjectId()));
		SiteInfo site = new SiteInfo();
		site.setProjectId(user.getProjectId());
		Collection<SiteInfoVo> list = siteInfoService.findSiteList(site);
		int j[] = new int[5];
		list.forEach(item->{
			String level = LevelAppraisal.Level(item,yinzi);
			if(level.equals("Ⅰ类")) j[0]++;
			else if(level.equals("Ⅱ类")) j[1]++;
			else if(level.equals("Ⅲ类")) j[2]++;
			else if(level.equals("Ⅳ类")) j[3]++;
			else if(level.equals("Ⅴ类")) j[4]++;
		});
		
		if(j[0]>0) {
			MapHeaderVo c = new MapHeaderVo();
			c.setName("Ⅰ类 " + String.valueOf(j[0]) + "个");
			c.setValue(String.valueOf(j[0]));
			b.add(c);
			a.add("Ⅰ类 " + String.valueOf(j[0]) + "个");
		}
		if(j[1]>0) {
			MapHeaderVo d = new MapHeaderVo();
			d.setName("Ⅱ类 " + String.valueOf(j[1]) + "个");
			d.setValue(String.valueOf(j[1]));
			a.add("Ⅱ类 " + String.valueOf(j[1]) + "个");
			b.add(d);
		}
		if(j[2]>0) {
			MapHeaderVo e = new MapHeaderVo();
			e.setName("Ⅲ类 " + String.valueOf(j[2]) + "个");
			e.setValue(String.valueOf(j[2]));
			b.add(e);
			a.add("Ⅲ类 " + String.valueOf(j[2]) + "个");
		}
		if(j[3]>0) {
			MapHeaderVo f = new MapHeaderVo();
			f.setName("Ⅳ类 " + String.valueOf(j[3]) + "个");
			f.setValue(String.valueOf(j[3]));
			b.add(f);
			a.add("Ⅳ类 " + String.valueOf(j[3]) + "个");
		}
		if(j[4]>0) {
			MapHeaderVo g = new MapHeaderVo();
			g.setName("Ⅴ类 " + String.valueOf(j[4]) + "个");
			g.setValue(String.valueOf(j[4]));
			b.add(g);
			a.add("Ⅴ类 " + String.valueOf(j[4]) + "个");
		}
		LevelVo vo =new LevelVo();
		vo.setCakeData(b);
		vo.setTransverseData(a);
		api.success(vo);
		return api;
	}
	
	/**
	 * 控制台实时数据-数据
	 * @author: 李正
	 * @param session
	 * @param site
	 * @param areaType
	 * @param areaName
	 * @return      
	 * @return: ResponseList      
	 * @throws
	 */
	@RequestMapping("/shishiData")
	public ResponseList shishiData(HttpSession session) {
		log.info("控制台实时数据");
		ResponseList responseList = new ResponseList();
		UserInfo user = UserHelper.getUserInfo(session);
		List<Object> vos = new ArrayList<>();
		try {
			Collection<YinziInfo> yinzi = yinziInfoService.listByMap(Map.of("project_id",user.getProjectId()));
			SiteInfo site = new SiteInfo();
			site.setProjectId(user.getProjectId());
			Collection<SiteInfoVo> list = siteInfoService.findSiteList(site);
		    DecimalFormat df = new DecimalFormat("#.########");
		    list.forEach(item -> {
		    	List<String> name = new ArrayList<>();
				List<String> value = new ArrayList<>();
				name.add("id");
				value.add(String.valueOf(item.getId()));
				if(redisUtil.hget(item.getAccessCode(), "state") == null) {
					name.add("state");
					value.add("离线");
				}else {
					name.add("state");
					value.add("在线");
				}
				
				name.add("siteName");
				value.add(item.getSiteName());
				name.add("accessCode");
				value.add(item.getAccessCode());
				name.add("addTime");
				if(item.getData().size() > 0)
				value.add(String.valueOf(item.getData().get(0).getCreateAt()).replace("T"," "));
				else
					value.add("");
				name.add("data");
				String data = " ";
				for(int j = 0; j<item.getData().size(); j++) {
					MonitoringDataVo m = item.getData().get(j);
					if(m.getYinziValue() == null || m.getYinziValue().equals(""))
						continue;
					else
						data += item.getData().get(j).getYinziName() + ":" + df.format(item.getData().get(j).getYinziValue()) + "、";
				}
				value.add(data.substring(0,data.length() - 1));
				String level = LevelAppraisal.Level(item,yinzi);
				name.add("level");
				value.add(level);
				Object object = new Object();
				try {
					object = DynamicFactory.dynamicClass(new MultipointVo(), name, value);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				vos.add(object);
		    });
		    responseList.success(vos);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseList;
	}
	
	/**
	 * @throws Exception 
	 * 控制台实时数据-表头
	 * @author: 李正
	 * @return      
	 * @return: ResponseApi      
	 * @throws
	 */
	@RequestMapping("/shishiHeader")
	public ResponseApi shishiHeader() throws Exception {
		ResponseApi api = new ResponseApi();
		List<Object> header = new ArrayList<>();
		TableHeadElement a = new TableHeadElement("id","ID"),
				h = new TableHeadElement("state","网络"),
				c = new TableHeadElement("data","监测数据"),
				d = new TableHeadElement("siteName","点位"),
				e = new TableHeadElement("addTime","监测时间"),
				b = new TableHeadElement("level","级别");
		e.setWidth(174);a.setWidth(50);h.setWidth(90);b.setWidth(93);
		header.add(a);header.add(h);header.add(d);header.add(e);header.add(c);header.add(b);		
		api.success(header);
		return api;
	}
}
