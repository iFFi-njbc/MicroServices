package com.MicroservicesBenifits.benifits_service.exception;

import org.springframework.http.HttpStatus;

public class ErrorDetails {
	
	/**
	 * 
	 */

	private String message;
	private String errorCode;
	
	private HttpStatus http;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public HttpStatus getHttp() {
		return http;
	}
	public void setHttp(HttpStatus http) {
		this.http = http;
	}
	@Override
	public String toString() {
		return "ErrorDetails [message=" + message + ", errorCode=" + errorCode +  ", http="
				+ http + "]";
	}
	public ErrorDetails() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ErrorDetails(String message, String errorCode, HttpStatus http) {
		super();
		this.message = message;
		this.errorCode = errorCode;
		//this.details = details;
		this.http = http;
	}


}
