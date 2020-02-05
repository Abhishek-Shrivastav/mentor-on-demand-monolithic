package com.mentorondemand.entity;

import java.util.List;

public class SkillList {

	private List<MentorSkill> listSkill;

	public SkillList() {}

	public SkillList(List<MentorSkill> listSkill) {
		
		this.listSkill = listSkill;
	}

	public List<MentorSkill> getListSkill() {
		return listSkill;
	}
}
