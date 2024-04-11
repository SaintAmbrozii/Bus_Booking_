package com.example.busbooking.repository;

import com.example.busbooking.domain.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepo extends JpaRepository<Payment,Long> {

}
