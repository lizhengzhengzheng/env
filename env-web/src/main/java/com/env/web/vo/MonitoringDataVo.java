package com.env.web.vo;

import java.time.LocalDateTime;

public class MonitoringDataVo {
	
	/**
     * 主键
     */
    private Integer id;

    /**
     * 站点id
     */
    private Integer siteId;

    /**
     * 因子id
     */
    private Integer yinziId;

    /**
     * 数据值
     */
    private Double yinziValue;

    /**
     * 数据上传流水号
     */
    private String serialNum;

    /**
     * 创建时间
     */
    private LocalDateTime createAt;

    /**
     * 状态【0：正常】【1：删除】
     */
    private Integer state;
    
    private String level;
    
    private String yinziName;
    
    private String yinziUnit;
    
    private String siteName;
    
    private String yinziNum;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSiteId() {
		return siteId;
	}

	public void setSiteId(Integer siteId) {
		this.siteId = siteId;
	}

	public Integer getYinziId() {
		return yinziId;
	}

	public void setYinziId(Integer yinziId) {
		this.yinziId = yinziId;
	}

	public Double getYinziValue() {
		return yinziValue;
	}

	public void setYinziValue(Double yinziValue) {
		this.yinziValue = yinziValue;
	}

	public String getSerialNum() {
		return serialNum;
	}

	public void setSerialNum(String serialNum) {
		this.serialNum = serialNum;
	}

	public LocalDateTime getCreateAt() {
		return createAt;
	}

	public void setCreateAt(LocalDateTime createAt) {
		this.createAt = createAt;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getYinziName() {
		return yinziName;
	}

	public void setYinziName(String yinziName) {
		this.yinziName = yinziName;
	}

	public String getYinziUnit() {
		return yinziUnit;
	}

	public void setYinziUnit(String yinziUnit) {
		this.yinziUnit = yinziUnit;
	}

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public String getYinziNum() {
		return yinziNum;
	}

	public void setYinziNum(String yinziNum) {
		this.yinziNum = yinziNum;
	}
    
    
}
