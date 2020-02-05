package com.mentorondemand.entity;

public class MentorSkillData {

	private Integer id;
	private String mentorName;
	private String technologyName;
	private Double avgRating;
	private String toc;
	private String prerequisites;
	private Double fee;
	
	public MentorSkillData() {}

	public MentorSkillData(Integer id, String mentorName, String technologyName, Double avgRating, String toc,
			String prerequisites, Double fee) {
		super();
		this.id = id;
		this.mentorName = mentorName;
		this.technologyName = technologyName;
		this.avgRating = avgRating;
		this.toc = toc;
		this.prerequisites = prerequisites;
		this.fee = fee;
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

	public String getTechnologyName() {
		return technologyName;
	}

	public void setTechnologyName(String technologyName) {
		this.technologyName = technologyName;
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

	@Override
	public String toString() {
		return "MentorSkillData [id=" + id + ", mentorName=" + mentorName + ", technologyName=" + technologyName
				+ ", avgRating=" + avgRating + ", toc=" + toc + ", prerequisites=" + prerequisites + ", fee=" + fee
				+ "]";
	}
}
