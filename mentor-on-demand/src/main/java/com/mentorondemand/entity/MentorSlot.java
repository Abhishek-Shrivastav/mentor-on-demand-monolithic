package com.mentorondemand.entity;

import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mentor_slots")
public class MentorSlot {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	@Column(name = "mentor_id")
	private Integer mentorId;
	@Column(name = "time_from")
	private Time timeFrom;
	@Column(name = "time_to")
	private Time timeTo;
	@Column(name = "isactive")
	private Integer active;
	
	public MentorSlot() {}

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

	public Integer getActive() {
		return active;
	}

	public void setActive(Integer active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "MentorSlot [id=" + id + ", mentorId=" + mentorId + ", timeFrom=" + timeFrom + ", timeTo=" + timeTo
				+ ", active=" + active + "]";
	}
}
