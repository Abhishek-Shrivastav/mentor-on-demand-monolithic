package com.mentorondemand.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mentorondemand.entity.MentorSkill;
import com.mentorondemand.entity.SkillList;
import com.mentorondemand.facade.MentorSkillDao;
import com.mentorondemand.facade.MentorSkillService;

@Service
public class MentorSkillServiceImpl implements MentorSkillService {

	@Autowired
	private MentorSkillDao dao;
	
	@Override
	public SkillList getAll() {
		
		SkillList list = new SkillList(this.dao.getAllSkill());

		return list;
	}

	@Override
	public MentorSkill getById(Integer id) {
		
		return this.dao.getSkillById(id);
	}

	@Override
	public Boolean save(MentorSkill skill) {
		
		return this.dao.saveSkill(skill);
	}

	@Override
	public Boolean update(MentorSkill skill) {
		
		return this.dao.updateSkill(skill);
	}

	@Override
	public Boolean delete(Integer id) {
		
		return this.dao.deleteSkill(id);
	}

	@Override
	public SkillList getMentorSkillByTechId(Integer id) {
		
		SkillList list = new SkillList(this.dao.getMentorSkillByTechId(id));
		
		return list;
	}

	@Override
	public SkillList getMentorSkillById(Integer id) {
		
		SkillList list = new SkillList(this.dao.getMentorSkillById(id));
		
		return list;
	}
	
	@Override
	public MentorSkill getSkillByMentorIdAndTechId(Integer mentorId,Integer techId) {
		
		return this.dao.getSkillByMentorIdAndTechId(mentorId,techId);
	}

	@Override
	public Boolean updateAvgSkill(Integer mentorId, Integer techId, Double avgRating) {
		
		return this.dao.updateAvgSkill(mentorId, techId, avgRating);
	}
}
