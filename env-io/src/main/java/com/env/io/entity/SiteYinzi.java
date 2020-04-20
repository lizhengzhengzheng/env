package com.env.io.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the site_yinzi database table.
 * 
 */
@Entity
@Table(name="site_yinzi")
@NamedQuery(name="SiteYinzi.findAll", query="SELECT s FROM SiteYinzi s")
public class SiteYinzi implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Column(name="analysis_method")
	private String analysisMethod;

	@Column(name="create_at")
	private Timestamp createAt;

	@Column(name="create_user")
	private int createUser;

	@Column(name="max_value")
	private double maxValue;

	@Column(name="min_value")
	private double minValue;

	@Column(name="site_id")
	private int siteId;

	@Column(name="state")
	private int state;

	@Column(name="yinzi_id")
	private int yinziId;

	public SiteYinzi() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAnalysisMethod() {
		return this.analysisMethod;
	}

	public void setAnalysisMethod(String analysisMethod) {
		this.analysisMethod = analysisMethod;
	}

	public Timestamp getCreateAt() {
		return this.createAt;
	}

	public void setCreateAt(Timestamp createAt) {
		this.createAt = createAt;
	}

	public int getCreateUser() {
		return this.createUser;
	}

	public void setCreateUser(int createUser) {
		this.createUser = createUser;
	}

	public double getMaxValue() {
		return this.maxValue;
	}

	public void setMaxValue(double maxValue) {
		this.maxValue = maxValue;
	}

	public double getMinValue() {
		return this.minValue;
	}

	public void setMinValue(double minValue) {
		this.minValue = minValue;
	}

	public int getSiteId() {
		return this.siteId;
	}

	public void setSiteId(int siteId) {
		this.siteId = siteId;
	}

	public int getState() {
		return this.state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getYinziId() {
		return this.yinziId;
	}

	public void setYinziId(int yinziId) {
		this.yinziId = yinziId;
	}

}