package com.ecotage.exception;

public class PaymentServiceException extends RuntimeException{
	
	public PaymentServiceException() {
		super();
	}

	public PaymentServiceException(final String message) {
		super(message);
	}

}
