package com.env.io.common;

import java.util.Map;

/**
 * 设备请求消息实体
 * @author lizheng
 * @date 2020年3月27日
 *
 */
public class DeviceRequest {
	
	private String type;
	
	private String id;
	
	private String cilentId;
	
	private int state;
	
	private Map<String, Object> map;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}

	public String getCilentId() {
		return cilentId;
	}

	public void setCilentId(String cilentId) {
		this.cilentId = cilentId;
	}
	
	
}
