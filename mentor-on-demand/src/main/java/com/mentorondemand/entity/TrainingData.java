package com.mentorondemand.entity;

public class TrainingData {

	private Integer id;
	private String mentorName;
	private String userName;
	private String slotTimeFrom;
	private String slotTimeTo;
	private String techName;
	private Integer progress;
	private String startDate;
	private String endDate;
	private Double totalFee;
	private Double amountReceived;
	private Integer installmentStatus;
	private Integer rating;
	private String request;

	public TrainingData() {}

	public TrainingData(Integer id, String mentorName, String userName, String slotTimeFrom, String slotTimeTo,
			String techName, Integer progress, String startDate, String endDate, Double totalFee,
			Double amountReceived, Integer installmentStatus, Integer rating, String request) {
		super();
		this.id = id;
		this.mentorName = mentorName;
		this.userName = userName;
		this.slotTimeFrom = slotTimeFrom;
		this.slotTimeTo = slotTimeTo;
		this.techName = techName;
		this.progress = progress;
		this.startDate = startDate;
		this.endDate = endDate;
		this.totalFee = totalFee;
		this.amountReceived = amountReceived;
		this.installmentStatus = installmentStatus;
		this.rating = rating;
		this.request = request;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMentorName() {
		return mentorName;
	}

	public void setMentorName(String mentorName) {
		this.mentorName = mentorName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getSlotTimeFrom() {
		return slotTimeFrom;
	}

	public void setSlotTimeFrom(String slotTimeFrom) {
		this.slotTimeFrom = slotTimeFrom;
	}

	public String getSlotTimeTo() {
		return slotTimeTo;
	}

	public void setSlotTimeTo(String slotTimeTo) {
		this.slotTimeTo = slotTimeTo;
	}

	public String getTechName() {
		return techName;
	}

	public void setTechName(String techName) {
		this.techName = techName;
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

	public void setAmountReceived(Double amountReceived) {
		this.amountReceived = amountReceived;
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

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	@Override
	public String toString() {
		return "TrainingData [id=" + id + ", mentorName=" + mentorName + ", userName=" + userName + ", slotTimeFrom="
				+ slotTimeFrom + ", slotTimeTo=" + slotTimeTo + ", techName=" + techName + ", progress=" + progress
				+ ", startDate=" + startDate + ", endDate=" + endDate + ", totalFee=" + totalFee + ", amountReceived="
				+ amountReceived + ", installmentStatus=" + installmentStatus + ", rating=" + rating + ", request="
				+ request + "]";
	}
}
