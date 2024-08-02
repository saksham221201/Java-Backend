package com.nagarro.employee_module.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecordNotFoundException extends RuntimeException {
	private String errorDescription;
	private int errorCode;

	public RecordNotFoundException(String errorDescription, int errorCode) {
		this.errorDescription = errorDescription;
		this.errorCode = errorCode;
	}
}

