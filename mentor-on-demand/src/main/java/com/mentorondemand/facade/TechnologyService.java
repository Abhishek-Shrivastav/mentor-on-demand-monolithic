package com.mentorondemand.facade;

import com.mentorondemand.entity.Technology;
import com.mentorondemand.entity.TechnologyList;

public interface TechnologyService {

	TechnologyList getAll();
	Technology getById(Integer id);
	boolean save(Technology tech);
	boolean update(Technology tech);
	boolean delete(Integer id);
}
