package com.tongyuan.pageModel;

/**
 * JSON
 * 
 * @author 孙宇
 * 
 */
public class JsonMessage implements java.io.Serializable {

	private boolean success = false;
	private String msg = "";
	private Object obj = null;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

}
