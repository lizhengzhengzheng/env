package com.env.web.vo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class HistoryDataVo {
		
	private String siteName;
	
	private LocalDateTime createAt;
	
	private List<MonitoringDataVo> data = new ArrayList<>();

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public LocalDateTime getCreateAt() {
		return createAt;
	}

	public void setCreateAt(LocalDateTime createAt) {
		this.createAt = createAt;
	}

	public List<MonitoringDataVo> getData() {
		return data;
	}

	public void setData(List<MonitoringDataVo> data) {
		this.data = data;
	}
	
	
}
