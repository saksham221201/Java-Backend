package com.nagarro.employee_module.exception;

import com.nagarro.employee_module.response.ErrorResponse;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLIntegrityConstraintViolationException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({ConstraintViolationException.class, IllegalArgumentException.class, SQLIntegrityConstraintViolationException.class})
    public ResponseEntity<ErrorResponse> constraintViolationExceptionHandler(Exception e){
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RecordNotFoundException.class)
    public ResponseEntity<ErrorResponse> resourceNotFoundExceptionHandler(RecordNotFoundException e){
        ErrorResponse errorResponse = new ErrorResponse(e.getErrorDescription(), e.getErrorCode());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponse> badRequestExceptionHandler(BadRequestException e){
        ErrorResponse errorResponse = new ErrorResponse(e.getErrorDescription(), e.getErrorCode());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
