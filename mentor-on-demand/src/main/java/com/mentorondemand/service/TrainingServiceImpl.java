package com.mentorondemand.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mentorondemand.entity.Training;
import com.mentorondemand.entity.TrainingList;
import com.mentorondemand.facade.TrainingDao;
import com.mentorondemand.facade.TrainingService;

@Service
public class TrainingServiceImpl implements TrainingService {

	@Autowired
	private TrainingDao dao;
	
	public TrainingList getAll() {
		
		TrainingList list = new TrainingList(this.dao.getAllTraining());
		
		return list;
	}

	public Training getById(Integer id) {
		
		return this.dao.getTrainingById(id);
	}

	public boolean save(Training skill) {
		
		return this.dao.saveTraining(skill);
	}

	public boolean update(Training skill) {
		
		return this.dao.updateTraining(skill);
	}

	public boolean delete(Integer id) {
		
		return this.dao.deleteTraining(id);
	}

	public TrainingList getTrainingById(Integer mentorId) {
		
		TrainingList list = new TrainingList(this.dao.getByTrainingId(mentorId));
		
		return list;
	}

	public boolean checkSlot(Integer slotId) {
		
		return this.dao.checkStatus(slotId);
	}

	public TrainingList getActionTraining(Integer id) {
		
		TrainingList list = new TrainingList(this.dao.getActionTraining(id));
		
		return list;
	}
	
	public TrainingList getActionTrainingMentorId(Integer id,Integer request) {
		
		TrainingList list = new TrainingList(this.dao.getActionTrainingByMentorId(id,request));
		
		return list;
	}

	public boolean userRequest(Integer trainingId, Integer id) {
		
		return this.dao.userRequest(trainingId, id);
	}

	public TrainingList getTrainingByUserId(Integer userId) {
		
		TrainingList list = new TrainingList(this.dao.getTrainingByUserId(userId));
		
		return list;
	}

	public Long getCountTraining(Integer mentorId, Integer techId) {
		
		return this.dao.getCountTraining(mentorId,techId);
	}

	public Boolean updateRating(Integer trainingId, Integer rating) {
		
		return this.dao.userRating(trainingId, rating);
	}

	public Double getAvgRating(Integer mentorId, Integer techId) {
		
		return this.dao.getAvgRating(mentorId,techId);
	}
	
	public TrainingList getTrainingByUserIdAndRequest(Integer userId,Integer request) {
		
		TrainingList list = new TrainingList(this.dao.getTrainingByUserIdAndRequest(userId, request));
		
		return list;
	}

	@Override
	public boolean acceptTraining(Training training) {
		
		return this.dao.acceptTraining(training);
	}

	@Override
	public boolean progressUpgrade(Training training) {

		return this.dao.progressUpgrade(training);
	}

	@Override
	public TrainingList getPaymentInstallment() {
		
		TrainingList list = new TrainingList(this.dao.getPaymentInstallment());
		
		return list;
	}

	@Override
	public boolean paymentMethod(Integer trainingId, Double amountRecived, Integer installmentStatus) {
		
		return this.dao.paymentMethod(trainingId, amountRecived, installmentStatus);
	}
}
