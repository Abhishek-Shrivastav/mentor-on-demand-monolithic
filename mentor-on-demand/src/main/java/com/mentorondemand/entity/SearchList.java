package com.mentorondemand.entity;

import java.util.List;

public class SearchList {

	private List<Search> searchList;
	private List<CountTraining> countTrainingList;

	public SearchList() {}

	public SearchList(List<Search> searchList,List<CountTraining> countTrainingList) {
		
		this.searchList = searchList;
		this.countTrainingList = countTrainingList;
	}

	public List<Search> getSearchList() {
		return searchList;
	}

	public List<CountTraining> getCountTrainingList() {
		return countTrainingList;
	}
}
