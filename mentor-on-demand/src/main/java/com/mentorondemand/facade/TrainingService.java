package com.mentorondemand.facade;

import com.mentorondemand.entity.Training;
import com.mentorondemand.entity.TrainingList;

public interface TrainingService {

	TrainingList getAll();
	Training getById(Integer id);
	boolean save(Training skill);
	boolean update(Training skill);
	boolean delete(Integer id);
	boolean checkSlot(Integer slotId);
	TrainingList getTrainingById(Integer mentorId);
	TrainingList getTrainingByUserId(Integer userId);
	TrainingList getActionTraining(Integer id);
	TrainingList getActionTrainingMentorId(Integer id,Integer request);
	boolean userRequest(Integer trainingId,Integer id);
	boolean acceptTraining(Training training);
	Long getCountTraining(Integer mentorId,Integer techId);
	Boolean updateRating(Integer trainingId,Integer rating);
	Double getAvgRating(Integer mentorId, Integer techId);
	TrainingList getTrainingByUserIdAndRequest(Integer userId,Integer request);
	boolean progressUpgrade(Training training);
	TrainingList getPaymentInstallment();
	boolean paymentMethod(Integer trainingId,Double amountRecived,Integer installmentStatus);
	TrainingList getRunningTraining(Integer id);
	Double getAvgRatingByMentorIdTechId(Integer mentorId,Integer techId);
	TrainingList getTrainingBySlotId(Integer slotId);
}
