package com.ecotage.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ecotage.exception.PaymentServiceException;
import com.ecotage.vo.AddPayment;
import com.ecotage.vo.ShowOrderDetails;
import com.ecotage.vo.ShowPayments;

@Service
public interface PaymentService {
	
	public List<ShowOrderDetails> addPayments(List<AddPayment> paymentDetails) throws PaymentServiceException;

}
