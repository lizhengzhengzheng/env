package com.env.io.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;

/**
 * The persistent class for the monitoring_data database table.
 * 
 */
@Entity
@Table(name="monitoring_data")
@NamedQuery(name="MonitoringData.findAll", query="SELECT m FROM MonitoringData m")
public class MonitoringData implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Column(name="create_at")
	private Timestamp createAt;

	@Column(name="serial_num")
	private String serialNum;

	@Column(name="site_id")
	private int siteId;

	@Column(name="yinzi_id")
	private int yinziId;

	@Column(name="yinzi_value")
	private double yinziValue;

	public MonitoringData() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Timestamp getCreateAt() {
		return this.createAt;
	}

	public void setCreateAt(Timestamp createAt) {
		this.createAt = createAt;
	}

	public String getSerialNum() {
		return this.serialNum;
	}

	public void setSerialNum(String serialNum) {
		this.serialNum = serialNum;
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

	public double getYinziValue() {
		return this.yinziValue;
	}

	public void setYinziValue(double yinziValue) {
		this.yinziValue = yinziValue;
	}

}