package com.mentorondemand.entity;

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
	//private Time timeFrom;
	private String timeFrom;
	@Column(name = "time_to")
	//private Time timeTo;
	private String timeTo;
	@Column(name = "isactive")
	private Integer active;

	public MentorSlot() {}

	public MentorSlot(Integer id, Integer mentorId, String timeFrom, String timeTo, Integer active) {
		super();
		this.id = id;
		this.mentorId = mentorId;
		this.timeFrom = timeFrom;
		this.timeTo = timeTo;
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

	public String getTimeFrom() {
		return timeFrom;
	}

	public void setTimeFrom(String timeFrom) {
		
		this.timeFrom = timeFrom;
	}

	public String getTimeTo() {
		return timeTo;
	}

	public void setTimeTo(String timeTo) {
		
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
