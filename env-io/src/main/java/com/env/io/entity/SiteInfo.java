package com.env.io.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the site_info database table.
 * 
 */
@Entity
@Table(name="site_info")
@NamedQuery(name="SiteInfo.findAll", query="SELECT s FROM SiteInfo s")
public class SiteInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Column(name="access_code")
	private String accessCode;

	@Column(name="create_at")
	private Timestamp createAt;

	@Column(name="create_user")
	private int createUser;

	@Column(name="device_name")
	private String deviceName;

	private double latitude;

	private double longitude;

	@Column(name="net_ip")
	private String netIp;

	@Column(name="networking_method")
	private int networkingMethod;

	@Column(name="project_id")
	private int projectId;

	@Column(name="sim_num")
	private String simNum;

	@Column(name="sim_store")
	private String simStore;

	@Column(name="site_address")
	private String siteAddress;

	@Column(name="site_area")
	private String siteArea;

	@Column(name="site_desc")
	private String siteDesc;

	@Column(name="site_img")
	private String siteImg;

	@Column(name="site_name")
	private String siteName;

	@Column(name="state")
	private int state;

	@Column(name="type_id")
	private int typeId;

	@Column(name="upload_frequency")
	private int uploadFrequency;

	public SiteInfo() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAccessCode() {
		return this.accessCode;
	}

	public void setAccessCode(String accessCode) {
		this.accessCode = accessCode;
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

	public String getDeviceName() {
		return this.deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public double getLatitude() {
		return this.latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return this.longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public String getNetIp() {
		return this.netIp;
	}

	public void setNetIp(String netIp) {
		this.netIp = netIp;
	}

	public int getNetworkingMethod() {
		return this.networkingMethod;
	}

	public void setNetworkingMethod(int networkingMethod) {
		this.networkingMethod = networkingMethod;
	}

	public int getProjectId() {
		return this.projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public String getSimNum() {
		return this.simNum;
	}

	public void setSimNum(String simNum) {
		this.simNum = simNum;
	}

	public String getSimStore() {
		return this.simStore;
	}

	public void setSimStore(String simStore) {
		this.simStore = simStore;
	}

	public String getSiteAddress() {
		return this.siteAddress;
	}

	public void setSiteAddress(String siteAddress) {
		this.siteAddress = siteAddress;
	}

	public String getSiteArea() {
		return this.siteArea;
	}

	public void setSiteArea(String siteArea) {
		this.siteArea = siteArea;
	}

	public String getSiteDesc() {
		return this.siteDesc;
	}

	public void setSiteDesc(String siteDesc) {
		this.siteDesc = siteDesc;
	}

	public String getSiteImg() {
		return this.siteImg;
	}

	public void setSiteImg(String siteImg) {
		this.siteImg = siteImg;
	}

	public String getSiteName() {
		return this.siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public int getState() {
		return this.state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getTypeId() {
		return this.typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	public int getUploadFrequency() {
		return this.uploadFrequency;
	}

	public void setUploadFrequency(int uploadFrequency) {
		this.uploadFrequency = uploadFrequency;
	}

}