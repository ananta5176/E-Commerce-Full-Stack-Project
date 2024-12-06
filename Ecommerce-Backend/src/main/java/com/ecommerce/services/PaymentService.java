package com.ecommerce.services;

import com.ecommerce.entities.Payment;

public interface PaymentService {
	Payment savePayment(Payment payment);
	Payment findPaymentById(int id);
}
