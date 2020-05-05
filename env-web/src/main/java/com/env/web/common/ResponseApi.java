package com.env.web.common;

public class ResponseApi {
	
	private boolean success;
	
	/**
	 * 返回信息
	 */
	private String msg;	
	
	
	/**
	 * 返回数据集
	 */
	private Object data;
	
	/**
	 * 返回码
	 */
	private String code;

	public void success(Object data){
		this.success = true;
		this.msg = "操作成功";
		this.data = data;
	}
	
	public void success(){
		this.success = true;
		this.msg = "操作成功";
	}
	
	public void fail(String msg){
		this.success = false;
		this.msg = msg;
	}
	
	public Boolean getSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
}
