package com.mentorondemand.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mentorondemand.entity.Training;
import com.mentorondemand.facade.TrainingDao;

@Repository
@Transactional
public class TrainingDaoImpl implements TrainingDao {

	@PersistenceContext
	private EntityManager entity;
	
	@SuppressWarnings("unchecked")
	public List<Training> getAllTraining() {
		
		List<Training> list = this.entity.createQuery("From Training").getResultList();
		
		return list;
	}

	public Training getTrainingById(Integer id) {
		
		Training training = this.entity.find(Training.class,id);
		
		return training;
	}

	public boolean saveTraining(Training skill) {
		
		this.entity.persist(skill);
		
		return true;
	}

	public boolean updateTraining(Training skill) {
		
		Training training = this.entity.find(Training.class,skill.getId());
		
		training.setMentorId(skill.getMentorId());
		training.setUserId(skill.getUserId());
		training.setSlotId(skill.getSlotId());
		training.setTechId(skill.getTechId());
		training.setProgress(skill.getProgress());
		training.setStartDate(skill.getStartDate());
		training.setEndDate(skill.getEndDate());
		training.setAmountReceived(skill.getAmountReceived());
		training.setRequest(skill.getRequest());
		training.setIsActive(skill.getIsActive());
		
		this.entity.flush();
		
		return true;
	}

	public boolean deleteTraining(Integer id) {
		
		Training training = this.entity.find(Training.class,id);
		this.entity.remove(training);
		
		return true;
	}

	public List<Training> getByTrainingId(Integer mentorId) {
		
		@SuppressWarnings("unchecked")
		List<Training> list = this.entity.createQuery("From Training Where mentorId="+mentorId).getResultList();
		
		return list;
	}
	
	public List<Training> getTrainingByUserId(Integer userId) {
		
		@SuppressWarnings("unchecked")
		List<Training> list = this.entity.createQuery("From Training Where userId="+userId).getResultList();
		
		return list;
	}

	public boolean checkStatus(Integer slotId) {
		
		@SuppressWarnings("unchecked")
		List<Training> training = this.entity.createQuery("From Training Where slotId="+slotId+" And isActive="+1+" And (request!=1 And request!=4)").getResultList();
		
		if(training.isEmpty())
			return true;
		
		return false;
	}

	@SuppressWarnings("unchecked")
	public List<Training> getActionTraining(Integer id) {
		
		List<Training> list = this.entity.createQuery("From Training Where userId="+id+" And (request=1 Or request=2)").getResultList();
		
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<Training> getActionTrainingByMentorId(Integer id,Integer request) {
		
		List<Training> list = this.entity.createQuery("From Training Where mentorId="+id+" And request="+request).getResultList();
		
		return list;
	}

	public boolean userRequest(Integer trainingId, Integer id) {
		
		Training training = this.entity.find(Training.class,trainingId);
		
		training.setRequest(id);
		
		this.entity.flush();
		
		return true;
	}

	public Long getCountTraining(Integer mentorId, Integer techId) {
		
		Long countTraining = (Long) this.entity.createQuery("Select Count(id) From Training Where mentorId="+mentorId+" And techId="+techId).getSingleResult();
		
		return countTraining;
	}

	public Boolean userRating(Integer trainingId, Integer rating) {
		
		Training training = this.entity.find(Training.class,trainingId);
		
		if(training.getRequest() == 3)
			training.setRating(rating);
		else
			training.setRating(1);	//exception code
		
		this.entity.flush();
		
		return true;
	}

	public Double getAvgRating(Integer mentorId, Integer techId) {
		
		Double countTraining = (Double) this.entity.createQuery("Select Avg(rating) From Training Where mentorId="+mentorId+" And techId="+techId).getSingleResult();
		
		return countTraining;
	}
	
	@SuppressWarnings("unchecked")
	public List<Training> getTrainingByUserIdAndRequest(Integer userId,Integer request) {
		
		List<Training> list = this.entity.createQuery("From Training Where mentorId="+userId+" And request="+request).getResultList();
		
		return list;
	}

	@Override
	public boolean acceptTraining(Training t) {
		
		Training training = this.entity.find(Training.class,t.getId());
		
		training.setStartDate(t.getStartDate());
		training.setEndDate(t.getEndDate());
		training.setRequest(t.getRequest());
		
		this.entity.flush();
		
		return true;
	}

	@Override
	public boolean progressUpgrade(Training t) {
		
		Training training = this.entity.find(Training.class,t.getId());
		
		training.setProgress(t.getProgress());
		
		this.entity.flush();
		
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Training> getPaymentInstallment() {
		
		List<Training> list = this.entity.createQuery("From Training Where request=3 And ((progress=0 And installmentStatus=0) Or (progress=25 And installmentStatus=1) Or (progress=50 And installmentStatus=2) Or (progress=75 And installmentStatus=3) Or (progress=100 And installmentStatus=4))").getResultList();
		
		return list;
	}

	@Override
	public boolean paymentMethod(Integer trainingId, Double amountRecived, Integer installmentStatus) {
		
		Training training = this.entity.find(Training.class,trainingId);
		Double newAmount = training.getAmountReceived() + amountRecived;
		
		training.setAmountReceived(newAmount);
		training.setInstallmentStatus(installmentStatus);
		
		this.entity.flush();
		
		return true;
	}

	@Override
	public List<Training> getRunningTraining(Integer id) {
		
		@SuppressWarnings("unchecked")
		List<Training> list = this.entity.createQuery("From Training Where request="+id).getResultList();
		
		return list;
	}

	@Override
	public Double getAvgRatingByMentorIdTechId(Integer mentorId, Integer techId) {
		
		Double avgRating = (Double) this.entity.createQuery("Select Avg(rating) From Training Where mentorId="+mentorId+" And techId="+techId).getSingleResult();
		
		return avgRating;
	}

	@Override
	public List<Training> getTrainingBySlotId(Integer slotId) {
		
		@SuppressWarnings("unchecked")
		List<Training> training = this.entity.createQuery("From Training Where slotId="+slotId+" And isActive="+1+" And (request!=1 And request!=4)").getResultList();
		
		return training;
	}
}
