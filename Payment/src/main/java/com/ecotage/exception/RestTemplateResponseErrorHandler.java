package com.ecotage.exception;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

public class RestTemplateResponseErrorHandler implements ResponseErrorHandler {

	@Override
	public boolean hasError(ClientHttpResponse response) throws IOException {
		System.out.println("In HasError : "
				+ (response.getStatusCode().is4xxClientError() || response.getStatusCode().is5xxServerError()));
		return (response.getStatusCode().is4xxClientError() || response.getStatusCode().is5xxServerError());
	}

	@Override
	public void handleError(ClientHttpResponse response) throws IOException {
		System.out.println("handleError  :  " + response.getStatusCode().value());
		if (response.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
			throw new PaymentServiceException("Payment not found in system");
		}
	}

}
