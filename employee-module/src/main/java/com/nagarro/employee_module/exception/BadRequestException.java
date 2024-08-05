package com.nagarro.employee_module.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BadRequestException extends RuntimeException{
    private String errorDescription;
    private int errorCode;

    public BadRequestException(String errorDescription, int errorCode) {
        this.errorDescription = errorDescription;
        this.errorCode = errorCode;
    }
}
