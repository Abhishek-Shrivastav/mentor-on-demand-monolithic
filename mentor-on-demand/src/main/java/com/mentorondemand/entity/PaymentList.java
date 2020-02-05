package com.mentorondemand.entity;

import java.util.List;

public class PaymentList {

	private List<Payment> listPayment;

	public PaymentList() {}

	public PaymentList(List<Payment> listPayment) {
		
		this.listPayment = listPayment;
	}

	public List<Payment> getListPayment() {
		return listPayment;
	}
}
