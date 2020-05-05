package com.env.web.vo;

import com.env.web.entity.ProjectInfo;

public class ProjectInfoVo extends ProjectInfo{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String createUserName;
	
	private String userNames;

	public String getCreateUserName() {
		return createUserName;
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}

	public String getUserNames() {
		return userNames;
	}

	public void setUserNames(String userNames) {
		this.userNames = userNames;
	}

}
