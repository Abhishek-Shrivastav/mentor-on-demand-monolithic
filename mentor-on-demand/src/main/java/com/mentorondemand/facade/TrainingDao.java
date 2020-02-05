package com.mentorondemand.facade;

import java.util.List;

import com.mentorondemand.entity.Training;

public interface TrainingDao {

	List<Training> getAllTraining();
	Training getTrainingById(Integer id);
	boolean saveTraining(Training skill);
	boolean updateTraining(Training skill);
	boolean deleteTraining(Integer id);
	boolean checkStatus(Integer slotId);
	List<Training> getByTrainingId(Integer mentorId);
	List<Training> getTrainingByUserId(Integer userId);
	List<Training> getActionTraining(Integer id);
	List<Training> getActionTrainingByMentorId(Integer id,Integer request);
	boolean userRequest(Integer trainingId,Integer id);
	boolean acceptTraining(Training training);
	Long getCountTraining(Integer mentorId,Integer techId);
	Boolean userRating(Integer trainingId,Integer rating);
	Double getAvgRating(Integer mentorId, Integer techId);
	List<Training> getTrainingByUserIdAndRequest(Integer userId,Integer request);
	boolean progressUpgrade(Training training);
	List<Training> getPaymentInstallment();
	boolean paymentMethod(Integer trainingId,Double amountRecived,Integer installmentStatus);
}
