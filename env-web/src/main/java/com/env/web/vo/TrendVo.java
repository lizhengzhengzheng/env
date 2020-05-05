package com.env.web.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * 趋势统计前端需要的数据
 * @author lz
 *
 */
public class TrendVo {
	
	/**
	 * 站点名称
	 */
	private String siteName;
	
	/**
	 * 因子名称
	 */
	private ArrayList<String>  yinzi;
	
	/**
	 * 监测时间
	 */
	private ArrayList<String>  date;
	
	/**
	 * 因子值等等一系列因子数据
	 */
	private List<MonitorVo> data;
	
	/**
	 * 判断有无数据  0:无   1：有
	 */
	private int isNull;
	
	private List<String> transverseData;
	private List<MapHeaderVo> cakeData;
	public List<String> getTransverseData() {
		return transverseData;
	}
	public void setTransverseData(List<String> transverseData) {
		this.transverseData = transverseData;
	}
	public List<MapHeaderVo> getCakeData() {
		return cakeData;
	}
	public void setCakeData(List<MapHeaderVo> cakeData) {
		this.cakeData = cakeData;
	}

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}
	
	public ArrayList<String> getYinzi() {
		return yinzi;
	}

	public void setYinzi(ArrayList<String> yinzi) {
		this.yinzi = yinzi;
	}

	public ArrayList<String> getDate() {
		return date;
	}

	public void setDate(ArrayList<String> date) {
		this.date = date;
	}

	public List<MonitorVo> getData() {
		return data;
	}

	public void setData(List<MonitorVo> data) {
		this.data = data;
	}

	public int getIsNull() {
		return isNull;
	}

	public void setIsNull(int isNull) {
		this.isNull = isNull;
	}
	
	
}
