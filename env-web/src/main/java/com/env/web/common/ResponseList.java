package com.env.web.common;

import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * layui列表返回
 * @author zhaoxin
 *
 */
public class ResponseList {

	/**
	 * 返回码
	 */
	private String code;
	
	/**
	 * 返回信息
	 */
	private String msg;
	
	/**
	 * 列表大小
	 */
	private Long count;
	
	/**
	 * 列表数据集合
	 */
	private List<?> data;
	
	public ResponseList fail(Exception e) {
		msg = e.getMessage();
		return this;
	}
	public void success(IPage<?> list) {
		code = "0";
		msg = "成功";
		count = list.getTotal();
		data = list.getRecords();
	}
	public void success(List<Object> list) {
		// TODO Auto-generated method stub
		code = "0";
		msg = "成功";
		count = Long.valueOf(list.size());
		data = list;
	}
	public void success() {
		code = "0";
		msg = "成功";
		data = null;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	public List<?> getData() {
		return data;
	}

	public void setData(List<?> data) {
		this.data = data;
	}

	public void setCount(long count) {
		this.count = count;
	}
	
}
