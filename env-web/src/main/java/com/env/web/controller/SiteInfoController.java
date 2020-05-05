package com.env.web.controller;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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
import com.env.web.entity.SiteInfo;
import com.env.web.entity.SiteYinzi;
import com.env.web.entity.UserInfo;
import com.env.web.entity.YinziInfo;
import com.env.web.service.DictionaryTypeService;
import com.env.web.service.RoleInfoService;
import com.env.web.service.SiteInfoService;
import com.env.web.service.SiteYinziService;
import com.env.web.service.YinziInfoService;
import com.env.web.util.DynamicFactory;
import com.env.web.util.LevelAppraisal;
import com.env.web.util.RedisUtil;
import com.env.web.util.TableHeadElement;
import com.env.web.vo.MapHeaderVo;
import com.env.web.vo.MonitoringDataVo;
import com.env.web.vo.MultipointVo;
import com.env.web.vo.SiteInfoVo;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2020-03-30
 */
@RestController
@RequestMapping("/site")
public class SiteInfoController {
	
	@Resource
	private SiteInfoService siteInfoService;
	@Resource
	private YinziInfoService yinziInfoService;
	@Resource
	private SiteYinziService siteYinziService;
	@Resource
	private DictionaryTypeService dictionaryTypeService;
	@Resource
	private RedisUtil redisUtil;
	@Autowired
	private RoleInfoService roleInfoService;
	
	/**
	 * <p>
	 * 功能描述：查询站点列表
	 * </p>
	 * 
	 * @param page  页码数
	 * @param limit 每页数量
	 * @return 站点列表
	 * @author lz
	 */
	@RequestMapping("/sitePage")
	public ResponseList sitePage(int page, int limit, HttpServletRequest request, HttpSession session, SiteInfo site) {
		ResponseList responseList = new ResponseList();
		UserInfo user = UserHelper.getUserInfo(session);
		site.setProjectId(user.getProjectId());
		site.setState(0);
		try {
			IPage<SiteInfoVo> list = siteInfoService.sitePage(new Page<SiteInfo>(page,limit), site);
			responseList.success(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseList;
	}
	
	
	
	/**
	 * 选择站点列表
	 * @author: 李正
	 * @param session
	 * @return      
	 * @return: ResponseList      
	 * @throws
	 */
	@RequestMapping("/siteList")
	public ResponseApi siteList(HttpSession session, Integer siteArea) {
		ResponseApi responseApi = new ResponseApi();
		SiteInfo site = new SiteInfo();
		UserInfo user = UserHelper.getUserInfo(session);
		site.setProjectId(user.getProjectId());
		try {
			QueryWrapper<SiteInfo> queryWrapper = new QueryWrapper<>();
			if(user.getProjectId() != 0) {
				queryWrapper.eq("project_id", user.getProjectId());
			}
			queryWrapper.eq("state", 0);
			if(user.getCategory() != 2) {
				queryWrapper.in("site_area", user.getSiteArea());
			}
			if(siteArea != null) {
				queryWrapper.eq("site_area", siteArea);
			}
			List<SiteInfo> list = siteInfoService.list(queryWrapper);
			responseApi.success(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseApi;
	}
	
	/**
	 * 选择站点列表
	 * @author: 李正
	 * @param session
	 * @return      
	 * @return: ResponseList      
	 * @throws
	 */
	@RequestMapping("/codeNameMap")
	public ResponseApi codeNameMap(HttpSession session) {
		ResponseApi responseApi = new ResponseApi();
		UserInfo user = UserHelper.getUserInfo(session);
		try {
			responseApi.success(siteInfoService.getCodeNameMap(user.getProjectId()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseApi;
	}
	
	/**
	 * 多点监控-数据
	 * @author: 李正
	 * @param session
	 * @param site
	 * @param areaType
	 * @param areaName
	 * @return      
	 * @return: ResponseList      
	 * @throws
	 */
	@RequestMapping("/multipoint")
	public ResponseList multipoint(HttpSession session,SiteInfo site) {
		ResponseList responseList = new ResponseList();
		UserInfo user = UserHelper.getUserInfo(session);
		site.setProjectId(user.getProjectId());
		List<Object> vos = new ArrayList<>();
		try {
			Collection<SiteInfoVo> list = siteInfoService.findSiteList(site);
			Map<Integer,String> areaMap = dictionaryTypeService.getDictionaryList(user.getProjectId(),4);
			list.forEach(item -> {
				List<String> name = new ArrayList<>();
				List<String> value = new ArrayList<>();
				name.add("id");
				value.add(String.valueOf(item.getId()));
				name.add("state");
				String state = redisUtil.hget(item.getAccessCode(), "state") == null? "离线":(String) redisUtil.hget(item.getAccessCode(), "state");	
				value.add(state);
				name.add("accessCode");
				value.add(item.getAccessCode());
				name.add("siteArea");
				value.add(areaMap.get(Integer.valueOf(item.getSiteArea())));
				name.add("siteName");
				value.add(item.getSiteName());
				name.add("addTime");
				value.add(String.valueOf(item.getData().get(0).getCreateAt()).replace("T"," "));
				DecimalFormat df=new DecimalFormat("#.########");
				name.add("data");
				String data = " ";
				for(int j = 0; j<item.getData().size(); j++) {
					data += item.getData().get(j).getYinziName() + ":" + df.format(item.getData().get(j).getYinziValue()) + "、";
				}
				value.add(data.substring(0,data.length() - 1));
				Object object = null;
				try {
					object = DynamicFactory.dynamicClass(new MultipointVo(), name, value);
					vos.add(object);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
			responseList.success(vos);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseList;
	}
	
	/**
	 * @throws Exception 
	 * 多点监控-表头
	 * @author: 李正
	 * @return      
	 * @return: ResponseApi      
	 * @throws
	 */
	@RequestMapping("/multipointHeader")
	public ResponseApi multipointHeader() throws Exception {
		ResponseApi api = new ResponseApi();
		List<Object> header = new ArrayList<>();
		TableHeadElement h = new TableHeadElement("state","网络"),
				b = new TableHeadElement("siteArea","区域"),
				d = new TableHeadElement("siteName","名称"),
				c = new TableHeadElement("data","监测数据"),
				e = new TableHeadElement("addTime","时间");
		h.setWidth(100);b.setWidth(200);d.setWidth(200);e.setWidth(200);
		header.add(h);header.add(b);header.add(d);header.add(e);header.add(c);
		api.success(header);
		return api;
	}
	
	/**
	 * 查询站点列表并携带最新监测数据返回前台
	 * @author: 李正
	 * @param page
	 * @param limit
	 * @param request
	 * @param session
	 * @return      
	 * @return: ResponseList      
	 * @throws
	 */
	@RequestMapping("/site_list_data")
	public ResponseApi siteListData(HttpSession session, String siteArea, String id) {
		SiteInfo site = new SiteInfo();
		if(siteArea != null && !siteArea.equals("")) {
			site.setSiteArea(Integer.valueOf(siteArea));
		}
		if(id != null && !id.equals("")) {
			site.setId(Integer.valueOf(id));
		}
		ResponseApi responseApi = new ResponseApi();
		UserInfo user = UserHelper.getUserInfo(session);
		site.setProjectId(user.getProjectId());
		try {
			Collection<SiteInfoVo> list = siteInfoService.findSiteList(site);
			List<SiteYinzi> siteYinzis = siteYinziService.findlist(user.getProjectId());
			Map<String,SiteYinzi> siteYinMap = new HashMap<>();
			siteYinzis.forEach(item -> {
				siteYinMap.put(item.getYinziId() + "" + item.getSiteId(), item);
			});
			QueryWrapper<YinziInfo> queryWrapper = new QueryWrapper<>();
			queryWrapper.eq("project_id", user.getProjectId());
			queryWrapper.eq("state", 0);
			Collection<YinziInfo> yinziInfos = yinziInfoService.list(queryWrapper);
			Map<Integer,YinziInfo> yinziMap = new HashMap<>();
			yinziInfos.forEach(yinziInfo -> {
				yinziMap.put(yinziInfo.getId(), yinziInfo);
			});
				//统计站点状态
				String state = "";
				for (SiteInfoVo item : list) {
					for (MonitoringDataVo data : item.getData()) {
						Double value = data.getYinziValue(),
						max = siteYinMap.get(data.getYinziId() + "" + data.getSiteId()).getMaxValue(),
						min = siteYinMap.get(data.getYinziId() + "" + data.getSiteId()).getMinValue();
						if(value != null) {
						if(value<min || value>max) 
							state = "数据超标";
						}
						String level = LevelAppraisal.Level(data,yinziMap.get(data.getYinziId()));
						data.setLevel(level);
					}
					state = redisUtil.hget(item.getAccessCode(), "state") == null? "离线":(String) redisUtil.hget(item.getAccessCode(), "state");	
					item.setSiteState(state);
				}
				responseApi.success(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseApi;
	}
	
	/**
	 * 统计站点在线数离线数超标数正常数
	 * @author: 李正
	 * @param session
	 * @param site
	 * @param areaType
	 * @param areaName
	 * @return      
	 * @return: ResponseApi      
	 * @throws
	 */
	@RequestMapping("/statistical_site")
	public ResponseApi statisticalSite(HttpSession session,String siteArea, String id) {
		SiteInfo site = new SiteInfo();
		if(siteArea != null && !siteArea.equals("")) {
			site.setSiteArea(Integer.valueOf(siteArea));
		}
		if(id != null && !id.equals("")) {
			site.setId(Integer.valueOf(id));
		}
		ResponseApi api = new ResponseApi();
		List<MapHeaderVo> vos = new ArrayList<MapHeaderVo>();	
		UserInfo user = UserHelper.getUserInfo(session);
		site.setProjectId(user.getProjectId());
		try {
			Collection<SiteInfoVo> list = siteInfoService.findSiteList(site);
			List<SiteYinzi> siteYinzis = siteYinziService.findlist(user.getProjectId());
			QueryWrapper<YinziInfo> queryWrapper = new QueryWrapper<>();
			queryWrapper.eq("project_id", user.getProjectId());
			queryWrapper.eq("state", 0);
			Map<String,SiteYinzi> map = new HashMap<>();
			siteYinzis.forEach(item -> {
				map.put(item.getYinziId() + "" + item.getSiteId(), item);
			});
			int chaobiao = 0, sum = list.size(), zaixian = 0, zhengchang = 0, lixian = 0; 
			//统计超标数量
			for (SiteInfoVo item : list) {
				for (MonitoringDataVo data : item.getData()) {
					Double value = data.getYinziValue(),
					max = map.get(data.getYinziId() + "" + data.getSiteId()).getMaxValue(),
					min = map.get(data.getYinziId() + "" + data.getSiteId()).getMinValue();
					if(value != null) {
						if(value<min || value>max) {
							chaobiao++;
							break;
						}
					}
				}
			}

			zhengchang = sum - chaobiao;
			
			//统计在线数量
			List<String> codes = siteInfoService.selectCode(user.getProjectId());
			for (String code : codes) {
				if(redisUtil.hasKey(code))
					zaixian ++;
			}
			lixian = sum - zaixian;

			
			//封装返回数据
			MapHeaderVo vo1 = new MapHeaderVo();
			vo1.setName("正常站点");
			vo1.setValue(String.valueOf(zhengchang));
			vos.add(vo1);
			MapHeaderVo vo2 = new MapHeaderVo();
			vo2.setName("超标站点");
			vo2.setValue(String.valueOf(chaobiao));
			vos.add(vo2);
			MapHeaderVo vo4 = new MapHeaderVo();
			vo4.setName("离线数");
			vo4.setValue(String.valueOf(lixian));
			vos.add(vo4);
			
			api.success();
			api.setData(vos);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return api;
	}

	/**
	 * 删除站点
	 * @param site 李正
	 */
	@RequestMapping("/delSite")
	public ResponseApi delSite(SiteInfo site, HttpSession session) {
		ResponseApi api = new ResponseApi();
		if(!roleInfoService.isHave(session, 41)) {
			api.fail("无权限");
			return api;
		}
		site.setState(1);
		siteInfoService.updateById(site);
		api.success();
		return api;
	}

	/**
	 * 图片上传
	 * @param servletRequest
	 * @param file
	 * @return
	 * @throws IOException
	 */
//	@RequestMapping(value = "/upload", method = RequestMethod.POST)
//	public Map<String, Object> upload(@RequestParam("file") MultipartFile file) throws IOException {
//		Map<String, Object> res = new HashMap<>();
//		// 如果文件内容不为空，则写入上传路径
//		if (!file.isEmpty()) {
//			log.info("文件名称" + file.getOriginalFilename());
//			// 上传文件名
//			String fileName = file.getOriginalFilename();
//			// 最后保存的文件路径
//			File filePath = getSaveFile(SITE_IMG_UPLOAD_PATH, fileName);
//			// 判断路径是否存在，没有就创建一个
//			if (!filePath.getParentFile().exists()) {
//				filePath.getParentFile().mkdirs();
//			}
//			file.transferTo(filePath);
//			// 返回一个url对象
//			res.put("url", getNetworkPath(SITE_IMG_UPLOAD_PATH, filePath.getName())); 
//		}
//		return res;
//	}

	/**
	 * 添加站点
	 * @param site
	 * @param session
	 */
	@RequestMapping("/addSite")
	public ResponseApi addSite(SiteInfo site, HttpSession session) {
		ResponseApi responseApi = new ResponseApi();
		if(!roleInfoService.isHave(session, 39)) {
			responseApi.fail("无权限");
			return responseApi;
		}
		UserInfo user = UserHelper.getUserInfo(session);
		site.setProjectId(user.getProjectId());
		QueryWrapper<SiteInfo> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("access_code", site.getAccessCode());
		queryWrapper.eq("state", 0);
		SiteInfo siteInfo = siteInfoService.getOne(queryWrapper,false);
		if(siteInfo == null) {
			site.setCreateUser(user.getId());
			siteInfoService.save(site);
			responseApi.success();
		}else {
			responseApi.fail("接入码已存在");
		}
		return responseApi;
	}
	
	
/**
 * 编辑站点信息
 * @author: 李正
 * @param site      
 * @return: void      
 * @throws
 */
	@RequestMapping("/editSite")
	public ResponseApi editSite(SiteInfo site, HttpSession session) {
		ResponseApi responseApi = new ResponseApi();
		if(!roleInfoService.isHave(session, 40)) {
			responseApi.fail("无权限");
			return responseApi;
		}
		SiteInfo a = siteInfoService.getById(site.getId());
		if(!a.getAccessCode().equals(site.getAccessCode())) {
			QueryWrapper<SiteInfo> queryWrapper = new QueryWrapper<>();
			queryWrapper.eq("state", 0);
			queryWrapper.eq("access_code", site.getAccessCode());
			SiteInfo b = siteInfoService.getOne(queryWrapper);
			if (b != null) {
				responseApi.fail("设备接入码已存在");
			} else {
				siteInfoService.updateById(site);
				responseApi.success();
			}
		}else {
			siteInfoService.updateById(site);
			responseApi.success();
		}
		return responseApi;
	}


}

