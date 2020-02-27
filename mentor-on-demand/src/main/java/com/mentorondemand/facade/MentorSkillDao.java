package com.mentorondemand.facade;

import java.util.List;

import com.mentorondemand.entity.MentorSkill;

public interface MentorSkillDao {

	List<MentorSkill> getAllSkill();
	MentorSkill getSkillById(Integer id);
	boolean saveSkill(MentorSkill skill);
	boolean updateSkill(MentorSkill skill);
	boolean deleteSkill(Integer id);
	List<MentorSkill> getMentorSkillByTechId(Integer id);
	List<MentorSkill> getMentorSkillById(Integer id);
	public MentorSkill getSkillByMentorIdAndTechId(Integer mentorId,Integer techId);
	boolean updateAvgSkill(Integer mentorId,Integer techId,Double avgRating);
	List<Integer> getAllUniqueSkill();
}
