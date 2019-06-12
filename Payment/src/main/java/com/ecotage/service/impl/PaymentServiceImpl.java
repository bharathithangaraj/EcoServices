package com.ecotage.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ecotage.dao.PaymentDAO;
import com.ecotage.exception.PaymentServiceException;
import com.ecotage.service.PaymentService;
import com.ecotage.vo.AddPayment;
import com.ecotage.vo.ShowOrderDetails;
import com.ecotage.vo.ShowPayments;

@Component
public class PaymentServiceImpl implements PaymentService {
	
	private static Logger log = LoggerFactory.getLogger(PaymentServiceImpl.class);
	
	@Autowired
	PaymentDAO paymentDao;

	@Override
	public List<ShowOrderDetails> addPayments(List<AddPayment> paymentDetails) throws PaymentServiceException {
		List<ShowOrderDetails> addOrders = null;

		try {
			addOrders = paymentDao.addPayments(paymentDetails);

			log.info(paymentDetails.toString());

		} catch (Exception px) {

			throw new PaymentServiceException("Unable to add Payment");
		}

		return addOrders;
	}

}
