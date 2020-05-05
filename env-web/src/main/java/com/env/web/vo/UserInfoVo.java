package com.env.web.vo;

import java.time.LocalDateTime;

public class UserInfoVo {
	
	/**
     * 主键
     */
    private Integer id;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String password;

    /**
     * 真实姓名
     */
    private String actualName;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 邮箱
     */
    private String email;

    /**
     * [1:系统管理员][2:项目管理员][3:站点管理员]
     */
    private Integer category;

    /**
     * 项目id
     */
    private Integer projectId;

    /**
     * 区域id集合
     */
    private String siteArea;

    /**
     * 状态【0：启用】【1：删除】
     */
    private Integer state;

    /**
     * 后台角色集合， 多个角色用英文逗号分隔
     */
    private String backstageRoleList;

    /**
     * 创建时间
     */
    private LocalDateTime createAt;

    /**
     * 创建人
     */
    private Integer createUser;
    
    /**
     * 创建人名称
     */
    private String createUserName;
    
    /**
     * 项目名称
     */
    private String projectname;
    
    /**
     * 区域
     */
    private String areaName;
    
    /**
     * 角色
     */
    private String roleName;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getActualName() {
		return actualName;
	}

	public void setActualName(String actualName) {
		this.actualName = actualName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getCategory() {
		return category;
	}

	public void setCategory(Integer category) {
		this.category = category;
	}

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public String getSiteArea() {
		return siteArea;
	}

	public void setSiteArea(String siteArea) {
		this.siteArea = siteArea;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getBackstageRoleList() {
		return backstageRoleList;
	}

	public void setBackstageRoleList(String backstageRoleList) {
		this.backstageRoleList = backstageRoleList;
	}

	public LocalDateTime getCreateAt() {
		return createAt;
	}

	public void setCreateAt(LocalDateTime createAt) {
		this.createAt = createAt;
	}

	public Integer getCreateUser() {
		return createUser;
	}

	public void setCreateUser(Integer createUser) {
		this.createUser = createUser;
	}

	public String getCreateUserName() {
		return createUserName;
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}

	public String getProjectname() {
		return projectname;
	}

	public void setProjectname(String projectname) {
		this.projectname = projectname;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
    
}
