package com.env.web.vo;

import java.time.LocalDateTime;

public class AlarmInfoVo {
	
	/**
     * 主键
     */
    private Integer id;

    /**
     * 站点id
     */
    private Integer siteId;
    
    private String siteName;
    
    private String siteAddress;

    /**
     * 因子id
     */
    private Integer yinziId;
    
    private String yinziName;
    
    private String yinziNum;

    /**
     * 报警类型id
     */
    private Integer alarmId;
    
    private String alarmName;

    /**
     * 报警类型1设备报警2站点报警
     */
    private Integer alarmType;

    /**
     * 报警时间
     */
    private LocalDateTime addTime;
    
    /**
     * 状态0未处理 1删除 2已处理
     */
    private Integer state;

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

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public Integer getYinziId() {
		return yinziId;
	}

	public void setYinziId(Integer yinziId) {
		this.yinziId = yinziId;
	}

	public String getYinziName() {
		return yinziName;
	}

	public void setYinziName(String yinziName) {
		this.yinziName = yinziName;
	}

	public String getYinziNum() {
		return yinziNum;
	}

	public void setYinziNum(String yinziNum) {
		this.yinziNum = yinziNum;
	}

	public Integer getAlarmId() {
		return alarmId;
	}

	public void setAlarmId(Integer alarmId) {
		this.alarmId = alarmId;
	}

	public String getAlarmName() {
		return alarmName;
	}

	public void setAlarmName(String alarmName) {
		this.alarmName = alarmName;
	}

	public Integer getAlarmType() {
		return alarmType;
	}

	public void setAlarmType(Integer alarmType) {
		this.alarmType = alarmType;
	}

	public LocalDateTime getAddTime() {
		return addTime;
	}

	public void setAddTime(LocalDateTime addTime) {
		this.addTime = addTime;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getSiteAddress() {
		return siteAddress;
	}

	public void setSiteAddress(String siteAddress) {
		this.siteAddress = siteAddress;
	}
    
    
    
    
}
