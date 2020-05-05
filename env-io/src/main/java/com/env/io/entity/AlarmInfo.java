package com.env.io.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the alarm_info database table.
 * 
 */
@Entity
@Table(name="alarm_info")
@NamedQuery(name="AlarmInfo.findAll", query="SELECT a FROM AlarmInfo a")
public class AlarmInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Column(name="add_time")
	private Timestamp addTime;

	@Column(name="alarm_id")
	private int alarmId;

	@Column(name="alarm_type")
	private int alarmType;

	@Column(name="site_id")
	private int siteId;

	@Column(name="yinzi_id")
	private int yinziId;

	public AlarmInfo() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Timestamp getAddTime() {
		return this.addTime;
	}

	public void setAddTime(Timestamp addTime) {
		this.addTime = addTime;
	}

	public int getAlarmId() {
		return this.alarmId;
	}

	public void setAlarmId(int alarmId) {
		this.alarmId = alarmId;
	}

	public int getAlarmType() {
		return this.alarmType;
	}

	public void setAlarmType(int i) {
		this.alarmType = i;
	}

	public int getSiteId() {
		return this.siteId;
	}

	public void setSiteId(int siteId) {
		this.siteId = siteId;
	}

	public int getYinziId() {
		return this.yinziId;
	}

	public void setYinziId(int yinziId) {
		this.yinziId = yinziId;
	}

}