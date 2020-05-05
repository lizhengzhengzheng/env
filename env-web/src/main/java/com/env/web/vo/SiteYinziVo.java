package com.env.web.vo;

import com.env.web.entity.SiteYinzi;

public class SiteYinziVo extends SiteYinzi{
	
	private static final long serialVersionUID = 1L;

	private String yinziName;
	
	private String siteName;
	
	private String createUserName;

	public String getYinziName() {
		return yinziName;
	}

	public void setYinziName(String yinziName) {
		this.yinziName = yinziName;
	}

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public String getCreateUserName() {
		return createUserName;
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}
	
	
}
