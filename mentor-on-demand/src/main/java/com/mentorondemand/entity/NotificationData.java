package com.mentorondemand.entity;

public class NotificationData {

	private Integer id;
	private String userName;
	private String techName;
	private String request;

	public NotificationData() {}

	public NotificationData(Integer id, String userName, String techName, String request) {
		super();
		this.id = id;
		this.userName = userName;
		this.techName = techName;
		this.request = request;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getTechName() {
		return techName;
	}

	public void setTechName(String techName) {
		this.techName = techName;
	}

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	@Override
	public String toString() {
		return "NotificationData [id=" + id + ", userName=" + userName + ", techName=" + techName + ", request="
				+ request + "]";
	}
}
