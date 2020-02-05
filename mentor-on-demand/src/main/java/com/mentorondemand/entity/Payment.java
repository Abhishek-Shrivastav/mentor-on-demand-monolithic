package com.mentorondemand.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "fee")
public class Payment {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "training_id")
	private Integer trainingId;
	@Column(name = "total_fees")
	private Double totalFees;
	@Column(name = "recived_amount")
	private Double recivedAmount;
	@Column(name = "remaining_payment")
	private Double remainingPayment;
	@Column(name = "payment	")
	private Double payment;
	@Column(name = "installment_status")
	private Integer installmentStatus;
	@Column(name = "date")
	private String dateTime;
	@Column(name = "payment_status")
	private Integer paymentStatus;

	public Payment() {}

	public Payment(Integer id, Integer trainingId, Double totalFees, Double recivedAmount, Double remainingPayment,
			Double payment, Integer installmentStatus, String dateTime, Integer paymentStatus) {
		super();
		this.id = id;
		this.trainingId = trainingId;
		this.totalFees = totalFees;
		this.recivedAmount = recivedAmount;
		this.remainingPayment = remainingPayment;
		this.payment = payment;
		this.installmentStatus = installmentStatus;
		this.dateTime = dateTime;
		this.paymentStatus = paymentStatus;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getTrainingId() {
		return trainingId;
	}

	public void setTrainingId(Integer trainingId) {
		this.trainingId = trainingId;
	}

	public Double getTotalFees() {
		return totalFees;
	}

	public void setTotalFees(Double totalFees) {
		this.totalFees = totalFees;
	}

	public Double getRecivedAmount() {
		return recivedAmount;
	}

	public void setRecivedAmount(Double recivedAmount) {
		this.recivedAmount = recivedAmount;
	}

	public Double getRemainingPayment() {
		return remainingPayment;
	}

	public void setRemainingPayment(Double remainingPayment) {
		this.remainingPayment = remainingPayment;
	}

	public Double getPayment() {
		return payment;
	}

	public void setPayment(Double payment) {
		this.payment = payment;
	}

	public Integer getInstallmentStatus() {
		return installmentStatus;
	}

	public void setInstallmentStatus(Integer installmentStatus) {
		this.installmentStatus = installmentStatus;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		dateTime = format.format(date);
		this.dateTime = dateTime;
	}

	public Integer getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(Integer paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	@Override
	public String toString() {
		return "Payment [id=" + id + ", trainingId=" + trainingId + ", totalFees=" + totalFees + ", recivedAmount="
				+ recivedAmount + ", remainingPayment=" + remainingPayment + ", payment=" + payment
				+ ", installmentStatus=" + installmentStatus + ", dateTime=" + dateTime + ", paymentStatus="
				+ paymentStatus + "]";
	}
}
