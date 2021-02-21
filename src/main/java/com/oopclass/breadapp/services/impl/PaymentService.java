package com.oopclass.breadapp.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oopclass.breadapp.models.Payment;
import com.oopclass.breadapp.repository.PaymentRepository;
import com.oopclass.breadapp.services.IPaymentService;

/**
 * OOP Class 20-21
 * @author Gerald Villaran
 */

@Service
public class PaymentService implements IPaymentService {
	
	@Autowired
	private PaymentRepository paymentRepository;
	
	@Override
	public Payment save(Payment entity) {
		return paymentRepository.save(entity);
	}

	@Override
	public Payment update(Payment entity) {
		return paymentRepository.save(entity);
	}

	@Override
	public void delete(Payment entity) {
		paymentRepository.delete(entity);
	}

	@Override
	public void delete(Long id) {
		paymentRepository.deleteById(id);
	}

	@Override
	public Payment find(Long id) {
		return paymentRepository.findById(id).orElse(null);
	}

	@Override
	public List<Payment> findAll() {
		return paymentRepository.findAll();
	}

	@Override
	public void deleteInBatch(List<Payment> payments) {
		paymentRepository.deleteInBatch(payments);
	}
	
}
