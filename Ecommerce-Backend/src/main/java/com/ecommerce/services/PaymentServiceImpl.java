package com.ecommerce.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.daos.PaymentDao;
import com.ecommerce.entities.Payment;

@Service
public class PaymentServiceImpl implements PaymentService {

	@Autowired PaymentDao dao;
	
	@Override
	public Payment savePayment(Payment payment) {
		// TODO Auto-generated method stub
		payment.setPaymentdate(new Date());
		return dao.save(payment);
	}

	@Override
	public Payment findPaymentById(int id) {
		// TODO Auto-generated method stub
		return dao.getById(id);
	}

}
