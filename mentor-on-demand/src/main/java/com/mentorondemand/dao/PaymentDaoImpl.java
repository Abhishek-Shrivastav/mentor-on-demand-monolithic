package com.mentorondemand.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mentorondemand.entity.Payment;
import com.mentorondemand.facade.PaymentDao;

@Repository
@Transactional
public class PaymentDaoImpl implements PaymentDao {

	@PersistenceContext
	private EntityManager entity;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Payment> getAllPayment() {

		List<Payment> list = this.entity.createQuery("From Payment Order By trainingId,dateTime").getResultList();
		
		return list;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Payment> getCompletePayment() {
		
		List<Payment> list = this.entity.createQuery("From Payment Where remainingPayment=0.0 And installmentStatus=5 And paymentStatus=1").getResultList();
		
		return list;
	}

	@Override
	public boolean savePayment(Payment payment) {

		Double amount = (payment.getRemainingPayment() - payment.getPayment());
		payment.setRemainingPayment(amount);
		
		this.entity.persist(payment);
		
		return true;
	}

	@Override
	public Payment getById(Integer id) {
		
		Payment pay = this.entity.find(Payment.class,id);
		
		return pay;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Payment> getPaymentByTraining(Integer id) {
		
		List<Payment> list = this.entity.createQuery("From Payment Where trainingId="+id).getResultList();
		
		return list;
	}

	@Override
	public Integer getTrainingId(Integer id) {
		
		Payment pay = this.entity.find(Payment.class,id);
		
		return pay.getTrainingId();
	}
}
