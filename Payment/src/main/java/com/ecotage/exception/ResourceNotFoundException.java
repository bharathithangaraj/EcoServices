package com.ecotage.exception;

public class ResourceNotFoundException extends Exception {
	
	int errorCode;
	
	public ResourceNotFoundException() {
		super();
	}
	
	public ResourceNotFoundException(String message) {
		super(message);
	}
	
	public ResourceNotFoundException (String message, int errorCode) {
		super(message);
		this.errorCode = errorCode;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public ResourceNotFoundException setErrorCode(int errorCode) {
		this.errorCode = errorCode;
		return this;
	}
	
	

}
