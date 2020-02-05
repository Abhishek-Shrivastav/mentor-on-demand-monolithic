package com.mentorondemand.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mentorondemand.entity.Technology;
import com.mentorondemand.facade.TechnologyDao;

@Repository
@Transactional
public class TechnologyDaoImpl implements TechnologyDao {

	@PersistenceContext
	private EntityManager entity;
	
	@SuppressWarnings("unchecked")
	public List<Technology> getAllTech() {
		
		List<Technology> list = this.entity.createQuery("From Technology").getResultList();
		
		return list;
	}

	public Technology getTechById(Integer id) {
		
		Technology tech = this.entity.find(Technology.class,id);
		
		return tech;
	}

	public boolean saveTech(Technology tech) {
		
		this.entity.persist(tech);
		
		return true;
	}

	public boolean updateTech(Technology tech) {
		
		Technology technology = this.entity.find(Technology.class,tech.getId());
		
		technology.setTechnologyName(tech.getTechnologyName());
		technology.setIsActive(tech.getIsActive());
		
		this.entity.flush();
		
		return true;
	}

	public boolean deleteTech(Integer id) {
		
		Technology tech = this.entity.find(Technology.class,id);
		this.entity.remove(tech);
		
		return true;
	}
}
