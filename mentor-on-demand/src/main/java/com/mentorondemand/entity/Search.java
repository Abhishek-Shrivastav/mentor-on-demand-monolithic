package com.mentorondemand.entity;

import java.sql.Time;

public class Search {

	//mentor details
	private Integer mentorId;
	private String mentorFirstName;
	private String mentorLastName;
	//@JsonIgnore	// to ignore 
	private Integer yearExp;
	private String linkedInUrl;
	//mentor skill
	private Integer techId;
	private String techName;
	private Integer skillId;
	private Double avgRating;
	private String toc;
	private String prerequisites;
	private Double fee;
	//mentor slot
	private Integer slotId;
	private Time timeFrom;
	private Time timeTo;
	
	public Search() {}
	
	public Search(Integer mentorId, String mentorFirstName, String mentorLastName, Integer yearExp, String linkedInUrl,
			Integer techId, String techName, Integer skillId, Double avgRating, String toc, String prerequisites, Double fee,
			Integer slotId, Time timeFrom, Time timeTo) {

		this.mentorId = mentorId;
		this.mentorFirstName = mentorFirstName;
		this.mentorLastName = mentorLastName;
		this.yearExp = yearExp;
		this.linkedInUrl = linkedInUrl;
		this.techId = techId;
		this.techName = techName;
		this.skillId = skillId;
		this.avgRating = avgRating;
		this.toc = toc;
		this.prerequisites = prerequisites;
		this.fee = fee;
		this.slotId = slotId;
		this.timeFrom = timeFrom;
		this.timeTo = timeTo;
	}
	
	public Integer getMentorId() {
		return mentorId;
	}

	public void setMentorId(Integer mentorId) {
		this.mentorId = mentorId;
	}

	public String getMentorFirstName() {
		return mentorFirstName;
	}

	public void setMentorFirstName(String mentorFirstName) {
		this.mentorFirstName = mentorFirstName;
	}

	public String getMentorLastName() {
		return mentorLastName;
	}

	public void setMentorLastName(String mentorLastName) {
		this.mentorLastName = mentorLastName;
	}

	public Integer getYearExp() {
		return yearExp;
	}

	public void setYearExp(Integer yearExp) {
		this.yearExp = yearExp;
	}

	public String getLinkedInUrl() {
		return linkedInUrl;
	}

	public void setLinkedInUrl(String linkedInUrl) {
		this.linkedInUrl = linkedInUrl;
	}

	public Integer getTechId() {
		return techId;
	}

	public void setTechId(Integer techId) {
		this.techId = techId;
	}

	public String getTechName() {
		return techName;
	}

	public void setTechName(String techName) {
		this.techName = techName;
	}

	public Integer getSkillId() {
		return skillId;
	}

	public void setSkillId(Integer skillId) {
		this.skillId = skillId;
	}

	public Double getAvgRating() {
		return avgRating;
	}

	public void setAvgRating(Double avgRating) {
		this.avgRating = avgRating;
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

	public Integer getSlotId() {
		return slotId;
	}

	public void setSlotId(Integer slotId) {
		this.slotId = slotId;
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

	@Override
	public String toString() {
		return "Search [mentorId=" + mentorId + ", mentorFirstName=" + mentorFirstName + ", mentorLastName="
				+ mentorLastName + ", yearExp=" + yearExp + ", linkedInUrl=" + linkedInUrl + ", techId=" + techId
				+ ", techName=" + techName + ", skillId=" + skillId + ", toc=" + toc + ", prerequisites="
				+ prerequisites + ", fee=" + fee + ", slotId=" + slotId + ", timeFrom=" + timeFrom + ", timeTo="
				+ timeTo + "]";
	}
}
