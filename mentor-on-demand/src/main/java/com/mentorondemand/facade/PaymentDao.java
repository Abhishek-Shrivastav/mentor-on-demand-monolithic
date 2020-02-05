package com.mentorondemand.facade;

import java.util.List;

import com.mentorondemand.entity.Payment;

public interface PaymentDao {

	List<Payment> getAllPayment();
	List<Payment> getCompletePayment();
	Payment getById(Integer id);
	boolean savePayment(Payment payment);
	List<Payment> getPaymentByTraining(Integer id);
	Integer getTrainingId(Integer id);
}
