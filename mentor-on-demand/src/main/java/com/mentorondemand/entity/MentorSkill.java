package com.mentorondemand.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "mentorskills")
public class MentorSkill {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	@Column(name = "mentor_id")
	private Integer mentorId;
	@NotNull(message = "Technology not provided!")
	@Column(name = "technology_id")
	private Integer technologyId;
	@Column(name = "avg_rating")
	private Double avgRating;
	@NotNull(message = "TOC not provided!")
	@Column(name = "toc")
	private String toc;
	@NotNull(message = "Prerequisite not provided!")
	@Column(name = "prerequisites")
	private String prerequisites;
	@DecimalMin(value = "0.0",message = "Enter in valid number format!")
	@DecimalMax(value = "100000.0", message = "Enter in valid number format!")
	@NotNull(message = "Fee not provide!")
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
