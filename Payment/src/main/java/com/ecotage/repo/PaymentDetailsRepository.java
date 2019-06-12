package com.ecotage.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecotage.model.PaymentDetails;

public interface PaymentDetailsRepository extends JpaRepository<PaymentDetails, Long> {

}
