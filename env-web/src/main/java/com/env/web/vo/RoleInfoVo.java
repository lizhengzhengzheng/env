package com.env.web.vo;

import java.time.LocalDateTime;

public class RoleInfoVo {
	
	/**
     * 主键
     */
    private Integer roleId;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 描述
     */
    private String roleDesc;

    /**
     * 可查看的菜单集合， 保存id，多个用英文逗号分隔
     */
    private String menuList;

    /**
     * 状态【0：启用】【1：删除】
     */
    private Integer state;

    /**
     * 所属项目id
     */
    private Integer projectId;

    /**
     * 创建时间
     */
    private LocalDateTime createAt;

    /**
     * 创建人
     */
    private Integer createUser;
    
    /**
     * 创建人
     */
    private String createUserName;

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleDesc() {
		return roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

	public String getMenuList() {
		return menuList;
	}

	public void setMenuList(String menuList) {
		this.menuList = menuList;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
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
    
    
    
}
