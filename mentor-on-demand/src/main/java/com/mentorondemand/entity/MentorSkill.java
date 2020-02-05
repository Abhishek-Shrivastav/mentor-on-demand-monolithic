package com.mentorondemand.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mentorskills")
public class MentorSkill {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	@Column(name = "mentor_id")
	private Integer mentorId;
	@Column(name = "technology_id")
	private Integer technologyId;
	@Column(name = "avg_rating")
	private Double avgRating;
	@Column(name = "toc")
	private String toc;
	@Column(name = "prerequisites")
	private String prerequisites;
	@Column(name = "fee")
	private Double fee;
	
	public MentorSkill() {}

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

	public Integer getTechnologyId() {
		return technologyId;
	}

	public void setTechnologyId(Integer technologyId) {
		this.technologyId = technologyId;
	}
	
	public Double getAvgRating() {
		return avgRating;
	}

	public void setAvgRating(Double avgRating2) {
		this.avgRating = avgRating2;
	}

	public String getToc() {
		return toc;
	}

	public void setToc(String toc) {
		this.toc = toc;
	}

	public String getPrerequisites() {
		return prerequisites;
	}

	public void setPrerequisites(String prerequisites) {
		this.prerequisites = prerequisites;
	}

	public Double getFee() {
		return fee;
	}

	public void setFee(Double fee) {
		this.fee = fee;
	}

	@Override
	public String toString() {
		return "MentorSkill [id=" + id + ", mentorId=" + mentorId + ", technologyId=" + technologyId + ", avgRating="
				+ avgRating + ", toc=" + toc + ", prerequisites=" + prerequisites + ", fee=" + fee + "]";
	}

	
}
