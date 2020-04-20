package com.env.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.alibaba.fastjson.JSON;
import com.env.web.common.RedisColumRalation;
import com.env.web.common.ResponseApi;
import com.env.web.common.UserHelper;
import com.env.web.util.RedisUtil;

@RestController
@RequestMapping("/deviceSend")
public class DeviceSendController {
	
	@Autowired
	private RestTemplate template;
	@Autowired
	private RedisUtil redisUtil;
	
	@RequestMapping("/send")
	public ResponseApi send(String type, String content, String deviceId, HttpSession session) {
		ResponseApi api = new ResponseApi();
		Map<String,String> map = new HashMap<>();
		if(content != null && !content.equals("")) {
			map.put("value", content);
		}
		map.put("type", type);
		map.put("id", deviceId);
		int userId = UserHelper.getUserId(session);
		long t = System.currentTimeMillis();
		String cilentId = userId + deviceId + t;
		map.put("cilentId", cilentId);
		MultiValueMap<String, Object> paramMap = new LinkedMultiValueMap<String, Object>();
		paramMap.add("msg", JSON.toJSONString(map));
		String address = (String) redisUtil.hget(deviceId, RedisColumRalation.address);
		if(address == null) {
			api.fail("设备不在线");
			return api;
		}
		String url = "http://" + address + "/web/deviceControl";
        // 1、使用postForObject请求接口
        String result = template.postForObject(url, paramMap, String.class);
        try {
        	result = (String) JSON.parseObject(result).get("msg");
        }catch (Exception e) {
			// TODO: handle exception
        	api.fail("系统异常");
        	return api;
		}
        api.success();
        api.setMsg(result);
        return api;
	}
}

