package com.env.web.util;

/**
 * 表头数据
 * @author zhaoxin
 *
 */
public class TableHeadElement {

	/**
	 * 表头id值
	 */
	private String field;
	
	/**
	 * 表头显示值
	 */
	private String title;
		
	private Integer width;
	
	public TableHeadElement() {}
	
	public TableHeadElement(String field, String title) {
		super();
		this.field = field;
		this.title = title;
	}

	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}
	
}
