package com.ecotage.controller;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ecotage.exception.PaymentServiceException;
import com.ecotage.exception.ResourceNotFoundException;
import com.ecotage.handler.PaymentHandler;
import com.ecotage.vo.AddPayment;
import com.ecotage.vo.ShowOrderDetails;
import com.ecotage.vo.ShowPayments;

@RestController
@CrossOrigin("*")
public class PaymentController {
	
	@Autowired
	PaymentHandler paymentHandler;
	
	@PostMapping(value = "/payment/add/")
	public List<ShowOrderDetails> addPayments(@RequestBody List<AddPayment> paymentDetails)
			throws ResourceNotFoundException, PaymentServiceException {

		return paymentHandler.addPayments(paymentDetails);
	}

}
