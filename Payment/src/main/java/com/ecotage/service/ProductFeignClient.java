package com.ecotage.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.ecotage.vo.ShowOrderDetails;

import com.ecotage.vo.AddOrders;

@FeignClient("product-service")
public interface ProductFeignClient {
	
	@PostMapping("/order/new/")
	public List<ShowOrderDetails> addOrders(@RequestBody LinkedList<AddOrders> orderList);
	
	

}
