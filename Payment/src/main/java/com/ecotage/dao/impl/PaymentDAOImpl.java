package com.ecotage.dao.impl;

import static com.ecotage.util.CommonUtil.CURRENT_TIME;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.ecotage.dao.PaymentDAO;
import com.ecotage.exception.PaymentServiceException;
import com.ecotage.model.Payment;
import com.ecotage.model.PaymentDetails;
import com.ecotage.repo.PaymentDetailsRepository;
import com.ecotage.repo.PaymentRepository;
import com.ecotage.service.ProductFeignClient;
import com.ecotage.vo.AddOrders;
import com.ecotage.vo.AddPayment;
import com.ecotage.vo.ShowOrderDetails;
import com.ecotage.vo.ShowPayments;
import com.ecotage.vo.User;

@Component
public class PaymentDAOImpl implements PaymentDAO {
	
	@Autowired
	PaymentRepository paymentRepo;
	
	@Autowired
	PaymentDetailsRepository paymentDetRepo;
	
	@Value("${product.url}")
	public String productServiceUrl ;
	
	@Autowired
	RestTemplate restTemplate;

	@Autowired
	RestTemplate template2;
	
	 @Autowired
	 private KafkaTemplate<String, User> kafkaTemplate;

	 private static final String TOPIC = "Kafka_Example_json";
	
//	@Autowired
//	ProductFeignClient fiegnClient;

	@Override
	public List<ShowOrderDetails> addPayments(List<AddPayment> paymentDetailList) throws PaymentServiceException {
		
		List<ShowOrderDetails> showOrders = new ArrayList<>();
		
		for(AddPayment paymentDetails : paymentDetailList) {
			LinkedList<AddOrders> addOrderList = new LinkedList<>();
		
		Payment payment = new Payment(paymentDetails.getPaymentType(), paymentDetails.getUserId(), paymentDetails.getOrderId(), 1, CURRENT_TIME, CURRENT_TIME);
		
		Payment paymentEntity = paymentRepo.save(payment);
		
		PaymentDetails paymentDetail = new PaymentDetails(paymentDetails.getCardHolderName(), paymentDetails.getCardNo(), paymentDetails.getExpDate(), paymentDetails.getCvv(), paymentDetails.getPrice(), 1, CURRENT_TIME, CURRENT_TIME, paymentEntity.getPaymentId());
		
		PaymentDetails payDetEntity = paymentDetRepo.save(paymentDetail);
		
	
		//addedPayments.add(showPayment);
		
		AddOrders order = new AddOrders();
		order.setCartId(paymentDetails.getCartId());
		order.setOfferId(paymentDetails.getOfferId());
		order.setOrderId(paymentDetails.getOrderId());
		order.setProductId(paymentDetails.getProductId());
		order.setQuantity(paymentDetails.getQuantity());
		order.setStatus(paymentDetails.getStatus());
		order.setTotal(paymentDetails.getTotal());
		order.setUserId(paymentDetails.getUserId());
		addOrderList.addLast(order);
		//List list = fiegnClient.addOrders(addOrderList);
		ShowOrderDetails orders = saveOrder(order);
		
		showOrders.add(orders);
		
		paymentEntity.setOrderId(orders.getOrderId());
		
		paymentRepo.save(paymentEntity);
		
		System.out.println("List --------------->"+orders.toString());
		
		
		
		
		}
		
		List<String> productName = new ArrayList<>();
		Double price= 0.0;
		Double total= 0.0;
		int quantity = 0;
		for(ShowOrderDetails orderItem : showOrders) {
			productName.add(orderItem.getProduct().getProductName());
			price = orderItem.getProduct().getPrice();
			total = orderItem.getTotal();
			quantity = orderItem.getQuantity();
			
		}
		
		kafkaTemplate.send(TOPIC, new User("bharathi", "bharathigragaraj@gmail.com", productName, price, total, quantity) );

       
		return showOrders;
		
	}
	
	
	public ShowOrderDetails saveOrder(AddOrders orders) {
		ResponseEntity<ShowOrderDetails> response = null;
		try {
		HttpEntity<AddOrders> entity = new HttpEntity<AddOrders>(orders);
		 response = restTemplate.exchange("http://localhost:8090/order/new/", HttpMethod.POST, entity, ShowOrderDetails.class);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return response.getBody();
	}
	
	
	/*public List<ShowOrderDetails> saveOrder(LinkedList<AddOrders> orders) {
		ResponseEntity<List> response = null;
		try {
		HttpEntity<LinkedList<AddOrders>> entity = new HttpEntity<LinkedList<AddOrders>>(orders);
		 response = restTemplate.exchange("http://localhost:8090/order/new/", HttpMethod.POST, entity, List.class);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return response.getBody();
	}
	*/
	/*public List saveOrder(LinkedList<AddOrders> orders) {
		ResponseEntity<List> response = null;
		try {
		HttpEntity<LinkedList<AddOrders>> entity = new HttpEntity<LinkedList<AddOrders>>(orders);
		 response = restTemplate.exchange("http://localhost:8090/order/new/", HttpMethod.POST, entity, List.class);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return response.getBody();
	}*/
	
	
	/*@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}*/
	


}
