package com.productmgnt.dto;

public class ApiResponse {

	private String message;

	private boolean is_Success;
	
	private boolean isError;
	
	private Object data;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isIs_Success() {
		return is_Success;
	}

	public void setIs_Success(boolean is_Success) {
		this.is_Success = is_Success;
	}

	public boolean isError() {
		return isError;
	}

	public void setError(boolean isError) {
		this.isError = isError;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public ApiResponse(String message, boolean is_Success, boolean isError, Object data) {
		super();
		this.message = message;
		this.is_Success = is_Success;
		this.isError = isError;
		this.data = data;
	}

	public ApiResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "ApiResponse [message=" + message + ", is_Success=" + is_Success + ", isError=" + isError + ", data="
				+ data + "]";
	}
	

	
}
