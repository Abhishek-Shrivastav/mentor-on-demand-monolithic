package com.mentorondemand.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "training")
public class Training {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	@Column(name = "mentor_id")
	private Integer mentorId;
	@Column(name = "user_id")
	private Integer userId;
	@Column(name = "slot_id")
	private Integer slotId;
	@Column(name = "tech_id")
	private Integer techId;
	@Column(name = "progress")
	private Integer progress;
	@Column(name = "start_date")
	private String startDate;
	@Column(name = "end_date")
	private String endDate;
	@Column(name = "total_fee")
	private Double totalFee;
	@Column(name = "amount_received")
	private Double amountReceived;
	@Column(name = "installment_status")
	private Integer installmentStatus;
	@Column(name = "rating")
	private Integer rating;
	@Column(name = "request")
	private Integer request;
	@Column(name = "isactive")
	private Integer isActive;
	
	public Training() {}

	public Training(Integer id, Integer mentorId, Integer userId, Integer slotId, Integer techId, Integer progress,
			String startDate, String endDate, Double totalFee, Double amountReceived, Integer installmentStatus,
			Integer rating, Integer request, Integer isActive) {
		super();
		this.id = id;
		this.mentorId = mentorId;
		this.userId = userId;
		this.slotId = slotId;
		this.techId = techId;
		this.progress = progress;
		this.startDate = startDate;
		this.endDate = endDate;
		this.totalFee = totalFee;
		this.amountReceived = amountReceived;
		this.installmentStatus = installmentStatus;
		this.rating = rating;
		this.request = request;
		this.isActive = isActive;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getMentorId() {
		return mentorId;
	}

	public void setMentorId(Integer mentorId) {
		this.mentorId = mentorId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getSlotId() {
		return slotId;
	}

	public void setSlotId(Integer slotId) {
		this.slotId = slotId;
	}

	public Integer getTechId() {
		return techId;
	}

	public void setTechId(Integer techId) {
		this.techId = techId;
	}

	public Integer getProgress() {
		return progress;
	}

	public void setProgress(Integer progress) {
		this.progress = progress;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public Double getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(Double totalFee) {
		this.totalFee = totalFee;
	}

	public Double getAmountReceived() {
		return amountReceived;
	}

	public void setAmountReceived(Double newAmount) {
		this.amountReceived = newAmount;
	}
	
	public Integer getInstallmentStatus() {
		return installmentStatus;
	}

	public void setInstallmentStatus(Integer installmentStatus) {
		this.installmentStatus = installmentStatus;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public Integer getRequest() {
		return request;
	}

	public void setRequest(Integer request) {
		this.request = request;
	}

	public Integer getIsActive() {
		return isActive;
	}

	public void setIsActive(Integer isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "Training [id=" + id + ", mentorId=" + mentorId + ", userId=" + userId + ", slotId=" + slotId
				+ ", techId=" + techId + ", progress=" + progress + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", totalFee=" + totalFee + ", amountReceived=" + amountReceived + ", installmentStatus="
				+ installmentStatus + ", rating=" + rating + ", request=" + request + ", isActive=" + isActive + "]";
	}
}
