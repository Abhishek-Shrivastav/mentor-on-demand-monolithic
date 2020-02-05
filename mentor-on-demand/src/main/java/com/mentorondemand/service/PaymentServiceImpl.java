package com.mentorondemand.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mentorondemand.entity.Payment;
import com.mentorondemand.entity.PaymentList;
import com.mentorondemand.facade.PaymentDao;
import com.mentorondemand.facade.PaymentService;

@Service
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	private PaymentDao dao;
	
	@Override
	public PaymentList getAllPayment() {
		
		PaymentList list = new PaymentList(this.dao.getAllPayment());
		
		return list;
	}
	
	@Override
	public PaymentList getCompletePayment() {
		
		PaymentList list = new PaymentList(this.dao.getCompletePayment());
		
		return list;
	}
	
	@Override
	public boolean savePayment(Payment payment) {
		
		return this.dao.savePayment(payment);
	}

	@Override
	public Payment getById(Integer id) {

		return this.dao.getById(id);
	}

	@Override
	public PaymentList getPaymentByTraining(Integer id) {
		
		PaymentList list = new PaymentList(this.dao.getPaymentByTraining(id));
		
		return list;
	}

	@Override
	public Integer getTrainingId(Integer id) {
		
		return this.dao.getTrainingId(id);
	}
}
