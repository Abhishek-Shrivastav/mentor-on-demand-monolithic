package com.mentorondemand.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mentorondemand.entity.MentorSkill;
import com.mentorondemand.facade.MentorSkillDao;

@Repository
@Transactional
public class MentorSkillDaoImpl implements MentorSkillDao {

	@PersistenceContext
	private EntityManager entity;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<MentorSkill> getAllSkill() {
		
		List<MentorSkill> mentorSkill = this.entity.createQuery("From MentorSkill").getResultList();
		
		return mentorSkill;
	}

	@Override
	public MentorSkill getSkillById(Integer id) {
		
		MentorSkill skill = this.entity.find(MentorSkill.class,id);
		
		return skill;
	}

	@Override
	public boolean saveSkill(MentorSkill skill) {
		
		this.entity.persist(skill);
		
		return true;
	}

	@Override
	public boolean updateSkill(MentorSkill skill) {
		
		MentorSkill mentorSkill = this.entity.find(MentorSkill.class,skill.getId());
		
		mentorSkill.setMentorId(skill.getMentorId());
		mentorSkill.setTechnologyId(skill.getTechnologyId());
		mentorSkill.setToc(skill.getToc());
		mentorSkill.setAvgRating(skill.getAvgRating());
		mentorSkill.setPrerequisites(skill.getPrerequisites());
		mentorSkill.setFee(skill.getFee());
		
		this.entity.flush();
		
		return true;
	}

	@Override
	public boolean deleteSkill(Integer id) {
		
		MentorSkill skill = this.entity.find(MentorSkill.class,id);
		this.entity.remove(skill);
		
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MentorSkill> getMentorSkillByTechId(Integer id) {
		
		List<MentorSkill> mentorSkill = this.entity.createQuery("From MentorSkill Where technologyId="+id).getResultList();
		
		return mentorSkill;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MentorSkill> getMentorSkillById(Integer id) {
		
		List<MentorSkill> mentorSkill = this.entity.createQuery("From MentorSkill Where mentorId="+id).getResultList();
		
		return mentorSkill;
	}

	@Override
	public MentorSkill getSkillByMentorIdAndTechId(Integer mentorId,Integer techId) {
		
		MentorSkill mentorSkill = (MentorSkill) this.entity.createQuery("From MentorSkill Where mentorId="+mentorId+" And technologyId="+techId).getSingleResult();
		
		return mentorSkill;
	}

	@Override
	public boolean updateAvgSkill(Integer mentorId, Integer techId, Double avgRating) {
		
		MentorSkill mentorSkill = (MentorSkill) this.entity.createQuery("From MentorSkill Where mentorId="+mentorId+" And technologyId="+techId).getSingleResult();
		
		mentorSkill.setAvgRating(avgRating);
		
		this.entity.flush();
		
		return true;
	}

	@Override
	public List<Integer> getAllUniqueSkill() {
		
		List<Integer> mentorSkill = this.entity.createQuery("Select Distinct technologyId From MentorSkill").getResultList();
		
		return mentorSkill;
	}
}
