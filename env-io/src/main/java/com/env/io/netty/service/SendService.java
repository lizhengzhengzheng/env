package com.env.io.netty.service;

import com.alibaba.fastjson.JSONObject;

/**
 * 发送命令接口
 * @author Administrator
 *
 */
public interface SendService {

	/**
	 * 发送通道
	 * @author zhaoxin
	 * @param deviceId 设备id
	 * @param content 发送的内容
	 * @return 客户端响应的数据
	 */
	String main(JSONObject msg) throws Exception;
	
}
