package com.mentorondemand.entity;

import java.util.List;

public class TrainingList {

	private List<Training> trainingList;

	public TrainingList() {}

	public TrainingList(List<Training> trainingList) {
		
		this.trainingList = trainingList;
	}

	public List<Training> getTrainingList() {
		return trainingList;
	}
}
