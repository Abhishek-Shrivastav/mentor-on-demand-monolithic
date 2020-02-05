package com.mentorondemand.facade;

import com.mentorondemand.entity.Payment;
import com.mentorondemand.entity.PaymentList;

public interface PaymentService {

	PaymentList getAllPayment();
	PaymentList getCompletePayment();
	Payment getById(Integer id);
	boolean savePayment(Payment payment);
	PaymentList getPaymentByTraining(Integer id);
	Integer getTrainingId(Integer id);
}
