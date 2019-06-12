package com.ecotage.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecotage.model.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

}
