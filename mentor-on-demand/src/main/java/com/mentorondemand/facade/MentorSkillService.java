package com.mentorondemand.facade;

import com.mentorondemand.entity.MentorSkill;
import com.mentorondemand.entity.SkillList;

public interface MentorSkillService {

	SkillList getAll();
	MentorSkill getById(Integer id);
	Boolean save(MentorSkill skill);
	Boolean update(MentorSkill skill);
	Boolean delete(Integer id);
	SkillList getMentorSkillByTechId(Integer id);
	SkillList getMentorSkillById(Integer id);
	MentorSkill getSkillByMentorIdAndTechId(Integer mentorId,Integer techId);
	Boolean updateAvgSkill(Integer mentorId,Integer techId,Double avgRating);
}
