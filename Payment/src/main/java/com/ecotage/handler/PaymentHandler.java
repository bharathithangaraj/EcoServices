package com.ecotage.handler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import com.ecotage.exception.PaymentServiceException;
import com.ecotage.exception.ResourceNotFoundException;
import com.ecotage.service.PaymentService;
import com.ecotage.vo.AddPayment;
import com.ecotage.vo.ShowOrderDetails;
import com.ecotage.vo.ShowPayments;

@Component
public class PaymentHandler {
	
	@Autowired
	PaymentService paymentService;
	
	@Autowired
	private Mapper mapper;
	
	
	public List<ShowOrderDetails> addPayments(@RequestBody List<AddPayment> paymentDetails)
			throws ResourceNotFoundException, PaymentServiceException {

		List<ShowOrderDetails> addOrders = null;
		try {
			addOrders = paymentService.addPayments(paymentDetails);

		} catch (PaymentServiceException px) {
			throw new PaymentServiceException("Internal Server Exception while add category");
		}
		return addOrders;
	}

}
