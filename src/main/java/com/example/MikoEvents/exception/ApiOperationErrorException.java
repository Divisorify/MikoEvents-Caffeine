package com.example.MikoEvents.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class ApiOperationErrorException extends RuntimeException {

	private HttpStatus httpStatus;

	public ApiOperationErrorException(String message) {
		super(message);
	}

	public ApiOperationErrorException(String message, HttpStatus httpStatus) {
		super(message);
		this.httpStatus = httpStatus;
	}

	public ApiOperationErrorException(String message, HttpStatus httpStatus, Throwable cause) {
		super(message, cause);
		this.httpStatus = httpStatus;
	}
}
