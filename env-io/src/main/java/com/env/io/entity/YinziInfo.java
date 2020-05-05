package com.env.io.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the yinzi_info database table.
 * 
 */
@Entity
@Table(name="yinzi_info")
@NamedQuery(name="YinziInfo.findAll", query="SELECT y FROM YinziInfo y")
public class YinziInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;
	
	@Column(name="project_id")
	private int projectId;

	@Column(name="create_at")
	private Timestamp createAt;

	@Column(name="create_user")
	private int createUser;

	@Column(name="lower_limit1")
	private double lowerLimit1;

	@Column(name="lower_limit2")
	private double lowerLimit2;

	@Column(name="lower_limit3")
	private double lowerLimit3;

	@Column(name="lower_limit4")
	private double lowerLimit4;

	@Column(name="lower_limit5")
	private double lowerLimit5;

	private String name;

	@Column(name="state")
	private int state;

	private String unit;

	@Column(name="upper_limit1")
	private double upperLimit1;

	@Column(name="upper_limit2")
	private double upperLimit2;

	@Column(name="upper_limit3")
	private double upperLimit3;

	@Column(name="upper_limit4")
	private double upperLimit4;

	@Column(name="upper_limit5")
	private double upperLimit5;

	@Column(name="yinzi_num")
	private String yinziNum;

	public YinziInfo() {
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

	public double getLowerLimit1() {
		return this.lowerLimit1;
	}

	public void setLowerLimit1(double lowerLimit1) {
		this.lowerLimit1 = lowerLimit1;
	}

	public double getLowerLimit2() {
		return this.lowerLimit2;
	}

	public void setLowerLimit2(double lowerLimit2) {
		this.lowerLimit2 = lowerLimit2;
	}

	public double getLowerLimit3() {
		return this.lowerLimit3;
	}

	public void setLowerLimit3(double lowerLimit3) {
		this.lowerLimit3 = lowerLimit3;
	}

	public double getLowerLimit4() {
		return this.lowerLimit4;
	}

	public void setLowerLimit4(double lowerLimit4) {
		this.lowerLimit4 = lowerLimit4;
	}

	public double getLowerLimit5() {
		return this.lowerLimit5;
	}

	public void setLowerLimit5(double lowerLimit5) {
		this.lowerLimit5 = lowerLimit5;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getState() {
		return this.state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getUnit() {
		return this.unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public double getUpperLimit1() {
		return this.upperLimit1;
	}

	public void setUpperLimit1(double upperLimit1) {
		this.upperLimit1 = upperLimit1;
	}

	public double getUpperLimit2() {
		return this.upperLimit2;
	}

	public void setUpperLimit2(double upperLimit2) {
		this.upperLimit2 = upperLimit2;
	}

	public double getUpperLimit3() {
		return this.upperLimit3;
	}

	public void setUpperLimit3(double upperLimit3) {
		this.upperLimit3 = upperLimit3;
	}

	public double getUpperLimit4() {
		return this.upperLimit4;
	}

	public void setUpperLimit4(double upperLimit4) {
		this.upperLimit4 = upperLimit4;
	}

	public double getUpperLimit5() {
		return this.upperLimit5;
	}

	public void setUpperLimit5(double upperLimit5) {
		this.upperLimit5 = upperLimit5;
	}

	public String getYinziNum() {
		return this.yinziNum;
	}

	public void setYinziNum(String yinziNum) {
		this.yinziNum = yinziNum;
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}
	
	

}