package com.mentorondemand.entity;

import java.sql.Time;

public class MentorSlotData {

	private Integer id;
	private Integer mentorId;
	private Time timeFrom;
	private Time timeTo;
	private String status;
	private String active;
	
	public MentorSlotData() {}

	public MentorSlotData(Integer id, Integer mentorId, Time timeFrom, Time timeTo, String status, String active) {
		super();
		this.id = id;
		this.mentorId = mentorId;
		this.timeFrom = timeFrom;
		this.timeTo = timeTo;
		this.status = status;
		this.active = active;
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

	public Time getTimeFrom() {
		return timeFrom;
	}

	public void setTimeFrom(Time timeFrom) {
		
		this.timeFrom = timeFrom;
	}

	public Time getTimeTo() {
		return timeTo;
	}

	public void setTimeTo(Time timeTo) {
		
		this.timeTo = timeTo;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "MentorSlotData [id=" + id + ", mentorId=" + mentorId + ", timeFrom=" + timeFrom + ", timeTo=" + timeTo
				+ ", status=" + status + ", active=" + active + "]";
	}
}
