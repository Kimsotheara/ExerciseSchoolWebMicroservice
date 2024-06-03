package com.theara.payment.service.repository;

import com.theara.payment.service.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
public interface PaymentRepository extends JpaRepository<Payment,Integer> {
    Optional<Payment> findByPayId(Integer id);
}
