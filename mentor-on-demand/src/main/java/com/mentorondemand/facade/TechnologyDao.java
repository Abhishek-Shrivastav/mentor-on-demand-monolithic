package com.mentorondemand.facade;

import java.util.List;

import com.mentorondemand.entity.Technology;

public interface TechnologyDao {

	List<Technology> getAllTech();
	Technology getTechById(Integer id);
	boolean saveTech(Technology tech);
	boolean updateTech(Technology tech);
	boolean deleteTech(Integer id);
}
