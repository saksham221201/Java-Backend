package com.nagarro.employee_module.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ErrorResponse {
	private String errorDescription;
	
	private int errorCode;
	
	private LocalDateTime timestamp;

	public ErrorResponse(String error, int code) {
		this.errorDescription = error;
		this.errorCode = code;
		this.timestamp = LocalDateTime.now();
	}

}
