package com.env.web.vo;

/**
 * 控制台 数据概览  项目简介  设备传输率
 * @author lz
 *
 */
public class ConsoleVo {
	private int siteNum;
	private int siteAlarm;
	private int equipAlarm;
	private int checkNum;
	public int getSiteNum() {
		return siteNum;
	}
	public void setSiteNum(int siteNum) {
		this.siteNum = siteNum;
	}
	public int getSiteAlarm() {
		return siteAlarm;
	}
	public void setSiteAlarm(int siteAlarm) {
		this.siteAlarm = siteAlarm;
	}
	public int getEquipAlarm() {
		return equipAlarm;
	}
	public void setEquipAlarm(int equipAlarm) {
		this.equipAlarm = equipAlarm;
	}
	public int getCheckNum() {
		return checkNum;
	}
	public void setCheckNum(int checkNum) {
		this.checkNum = checkNum;
	}
	
}
