package com.mentorondemand.entity;

import java.util.List;

public class TechnologyList {

	private List<Technology> techList;

	public TechnologyList(List<Technology> techList) {
		
		this.techList = techList;
	}

	public List<Technology> getTechList() {
		return techList;
	}
}
