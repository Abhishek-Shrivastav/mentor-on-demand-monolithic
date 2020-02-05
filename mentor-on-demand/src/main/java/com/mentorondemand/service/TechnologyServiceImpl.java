package com.mentorondemand.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mentorondemand.entity.Technology;
import com.mentorondemand.entity.TechnologyList;
import com.mentorondemand.facade.TechnologyDao;
import com.mentorondemand.facade.TechnologyService;

@Service
public class TechnologyServiceImpl implements TechnologyService {

	@Autowired
	private TechnologyDao dao;
	
	public TechnologyList getAll() {
		
		TechnologyList list = new TechnologyList(this.dao.getAllTech());
		
		return list;
	}

	public Technology getById(Integer id) {
		
		return this.dao.getTechById(id);
	}

	public boolean save(Technology tech) {
		
		return this.dao.saveTech(tech);
	}

	public boolean update(Technology tech) {
		
		return this.dao.updateTech(tech);
	}

	public boolean delete(Integer id) {
		
		return this.dao.deleteTech(id);
	}
}
