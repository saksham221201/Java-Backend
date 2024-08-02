package com.nagarro.employee_module.response;

import java.time.LocalDateTime;

public class ErrorResponse {
	private String errorDescription;
	
	private int errorCode;
	
	private LocalDateTime timestamp;

	public ErrorResponse() {
		super();
	}

	public ErrorResponse(String error, int code) {
		this.errorDescription = error;
		this.errorCode = code;
		this.timestamp = LocalDateTime.now();
	}

	public String getError() {
		return errorDescription;
	}
	public void setError(String error) {
		this.errorDescription = error;
	}

	public int getCode() {
		return errorCode;
	}
	public void setCode(int code) {
		this.errorCode = code;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}
}
