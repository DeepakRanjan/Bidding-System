package com.bidding.system.Bidding.System.beans;

public class JsonResponse {

	public static final boolean SUCCESS = true;
	
	public static final boolean FAILURE = false;
	
	private boolean status;
	
	private String message;
	
	private Object result;
	
	private Integer code;

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return "JsonResponse [status=" + status + ", message=" + message + ", result=" + result + ", code=" + code
				+ "]";
	}
}

