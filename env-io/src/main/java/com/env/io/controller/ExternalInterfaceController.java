package com.env.io.controller;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.env.io.common.DeviceCommon;
import com.env.io.common.SpringUtil;
import com.env.io.netty.service.SendService;

@RestController
@RequestMapping("/web")
public class ExternalInterfaceController {
		
	@RequestMapping("/deviceControl")
	public String deviceControl(String msg) {
		try {
			JSONObject obj=JSON.parseObject(msg);
			SendService sendService = (SendService) SpringUtil.getBean((String)obj.get("type"));
			String response =sendService.main(obj);
			return response;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Map<String,String> map = new HashMap<>();
			map.put("state","" + DeviceCommon.ERR);
			map.put("msg","系统异常");
			return JSON.toJSONString(map);
		}
	}
}
