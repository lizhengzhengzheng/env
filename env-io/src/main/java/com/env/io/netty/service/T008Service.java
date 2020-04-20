package com.env.io.netty.service;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.env.io.common.DeviceCommon;
import com.env.io.common.DeviceRequest;
import com.env.io.common.RedisUtil;
import com.env.io.common.SpringUtil;
import com.env.io.dao.SiteInfoRepository;
import com.env.io.entity.SiteInfo;

@Service("T008")
public class T008Service implements SendService{
	
	private RedisUtil redisUtil = SpringUtil.getBean(RedisUtil.class);
	
	@Resource
	private SiteInfoRepository siteInfoRepository;

	@Override
	public String main(JSONObject msg) throws Exception {
		// TODO Auto-generated method stub
		DeviceCommon.send(msg);
		Map<String,String> map = new HashMap<>();
		// 获取返回结果
		long t1 = System.currentTimeMillis();
		while(true) {
			long t2 = System.currentTimeMillis();
            if(t2-t1 > 5000){
            	map.put("state","" + DeviceCommon.ERR);
				map.put("msg","设备无响应");
				break;
            }
            Thread.sleep(250);
			DeviceRequest result = (DeviceRequest) redisUtil.get((String)msg.get("cilentId"));
			if(result != null) {
				map.put("state",String.valueOf(result.getState()));
				String a = (String)result.getMap().get("msg");
				map.put("msg", a==null||a.equals("")?"操作成功":a);
				if(result.getState() == DeviceCommon.OK) {
					SiteInfo site = siteInfoRepository.findByAccessCodeAndState((String)msg.get("id"), 0);
					site.setUploadFrequency(Integer.valueOf((String)msg.get("value")));
					siteInfoRepository.save(site);
				}
				redisUtil.del((String)msg.get("cilentId"));
				break;
			}
		}
		return JSON.toJSONString(map);
	}

}
