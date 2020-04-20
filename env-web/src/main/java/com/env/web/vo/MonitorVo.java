package com.env.web.vo;

import java.util.ArrayList;

/**
 * 趋势统计前端需要的数据
 * @author lz
 *
 */
public class MonitorVo {
	
	/**
	 * 因子名称
	 */
	private String name;
	
	/**
	 * 前端属性 默认为line
	 */
	private String type;
	
	/**
	 * 因子值
	 */
	private ArrayList<String> data;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public ArrayList<String> getData() {
		return data;
	}

	public void setData(ArrayList<String> data) {
		this.data = data;
	}

	

	

	
	
	
}
