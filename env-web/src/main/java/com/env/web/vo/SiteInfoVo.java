package com.env.web.vo;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author ${author}
 * @since 2020-03-30
 */
public class SiteInfoVo {

    /**
     * 主键
     */
    private Integer id;

    /**
     * 站点名称
     */
    private String siteName;

    /**
     * 区域
     */
    private Integer siteArea;

    /**
     * 项目id
     */
    private Integer projectId;

    /**
     * 站点类型id
     */
    private Integer typeId;

    /**
     * 站点地址
     */
    private String siteAddress;

    /**
     * 站点描述
     */
    private String siteDesc;

    /**
     * 经度
     */
    private Double longitude;

    /**
     * 纬度
     */
    private Double latitude;

    /**
     * 站点图片
     */
    private String siteImg;

    /**
     * 设备名称
     */
    private String deviceName;

    /**
     * 设备编号
     */
    private String accessCode;

    /**
     * ip地址
     */
    private String netIp;

    /**
     * SIM卡号
     */
    private String simNum;

    /**
     * 运营商
     */
    private String simStore;

    /**
     * 上网方式[1:WiFi][2:有线][3:SIM卡],
     */
    private Integer networkingMethod;

    /**
     * 状态【0：启用】【1：删除】
     */
    private Integer state;

    /**
     * 数据上传频率。分钟
     */
    private Integer uploadFrequency;

    /**
     * 创建人
     */
    private Integer createUser;

    /**
     * 添加时间
     */
    private LocalDateTime createAt;
    
    /**
     * 各种因子信息
     */
    private List<MonitoringDataVo> data;
    
    /**
     * 站点状态
     */
    private String siteState;
    
    /**
     * 创建人
     */
    private String createUserName;
    
    /**
     * 区域名称
     */
    private String areaName;
    
    /**
     * 项目名称
     */
    private String projectName;
    
    /**
     * 负责人
     */
    private String manager;
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public Integer getSiteArea() {
        return siteArea;
    }

    public void setSiteArea(Integer siteArea) {
        this.siteArea = siteArea;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getSiteAddress() {
        return siteAddress;
    }

    public void setSiteAddress(String siteAddress) {
        this.siteAddress = siteAddress;
    }

    public String getSiteDesc() {
        return siteDesc;
    }

    public void setSiteDesc(String siteDesc) {
        this.siteDesc = siteDesc;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public String getSiteImg() {
        return siteImg;
    }

    public void setSiteImg(String siteImg) {
        this.siteImg = siteImg;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getAccessCode() {
        return accessCode;
    }

    public void setAccessCode(String accessCode) {
        this.accessCode = accessCode;
    }

    public String getNetIp() {
        return netIp;
    }

    public void setNetIp(String netIp) {
        this.netIp = netIp;
    }

    public String getSimNum() {
        return simNum;
    }

    public void setSimNum(String simNum) {
        this.simNum = simNum;
    }

    public String getSimStore() {
        return simStore;
    }

    public void setSimStore(String simStore) {
        this.simStore = simStore;
    }

    public Integer getNetworkingMethod() {
        return networkingMethod;
    }

    public void setNetworkingMethod(Integer networkingMethod) {
        this.networkingMethod = networkingMethod;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getUploadFrequency() {
        return uploadFrequency;
    }

    public void setUploadFrequency(Integer uploadFrequency) {
        this.uploadFrequency = uploadFrequency;
    }

    public Integer getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Integer createUser) {
        this.createUser = createUser;
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

    public String getSiteState() {
		return siteState;
	}

	public void setSiteState(String siteState) {
		this.siteState = siteState;
	}

	public String getCreateUserName() {
		return createUserName;
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	
}
