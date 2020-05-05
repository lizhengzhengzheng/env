package com.env.io.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the dictionary_type database table.
 * 
 */
@Entity
@Table(name="dictionary_type")
@NamedQuery(name="DictionaryType.findAll", query="SELECT d FROM DictionaryType d")
public class DictionaryType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Column(name="alarm_num")
	private String alarmNum;

	@Column(name="create_at")
	private Timestamp createAt;

	@Column(name="create_user")
	private int createUser;

	private String name;

	@Column(name="project_id")
	private int projectId;

	@Column(name="state")
	private int state;

	@Column(name="type")
	private int type;

	public DictionaryType() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAlarmNum() {
		return this.alarmNum;
	}

	public void setAlarmNum(String alarmNum) {
		this.alarmNum = alarmNum;
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

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getProjectId() {
		return this.projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public int getState() {
		return this.state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getType() {
		return this.type;
	}

	public void setType(int type) {
		this.type = type;
	}

}