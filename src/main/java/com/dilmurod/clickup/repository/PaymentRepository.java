package com.dilmurod.clickup.repository;

import com.dilmurod.clickup.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
