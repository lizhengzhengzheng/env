package com.env.io.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the project_info database table.
 * 
 */
@Entity
@Table(name="project_info")
@NamedQuery(name="ProjectInfo.findAll", query="SELECT p FROM ProjectInfo p")
public class ProjectInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Column(name="create_at")
	private Timestamp createAt;

	@Column(name="create_user")
	private int createUser;

	@Column(name="project_address")
	private String projectAddress;

	@Column(name="project_desc")
	private String projectDesc;

	@Column(name="project_name")
	private String projectName;

	@Column(name="push_url")
	private String pushUrl;

	@Column(name="state")
	private int state;

	@Column(name="user_id")
	private String userId;

	public ProjectInfo() {
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

	public int getCreateUser() {
		return this.createUser;
	}

	public void setCreateUser(int createUser) {
		this.createUser = createUser;
	}

	public String getProjectAddress() {
		return this.projectAddress;
	}

	public void setProjectAddress(String projectAddress) {
		this.projectAddress = projectAddress;
	}

	public String getProjectDesc() {
		return this.projectDesc;
	}

	public void setProjectDesc(String projectDesc) {
		this.projectDesc = projectDesc;
	}

	public String getProjectName() {
		return this.projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getPushUrl() {
		return this.pushUrl;
	}

	public void setPushUrl(String pushUrl) {
		this.pushUrl = pushUrl;
	}

	public int getState() {
		return this.state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}