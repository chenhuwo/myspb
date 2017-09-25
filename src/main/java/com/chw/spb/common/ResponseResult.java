package com.chw.spb.common;
/**
* @Author chw
* @Date 2017/9/19
* @Description
*/

public class ResponseResult {
	
	private boolean success;
	
	
	private String msg;
	
	
	private Object data;
	
	
	private ResponseResult() {
		
	}
	
	
	private ResponseResult(boolean success, String msg, Object data) {
		super();
		this.success = success;
		this.msg = msg;
		this.data = data;
	}


	public static ResponseResult successMsg(String msg) {
		return new ResponseResult(true, msg,null);
	}
	
	public static ResponseResult failedMsg(String msg) {
		return new ResponseResult(false, msg,null);
	}


	public boolean isSuccess() {
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
	
	
}
