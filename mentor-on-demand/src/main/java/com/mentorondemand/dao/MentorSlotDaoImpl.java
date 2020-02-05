package com.mentorondemand.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mentorondemand.entity.MentorSlot;
import com.mentorondemand.facade.MentorSlotDao;

@Repository
@Transactional
public class MentorSlotDaoImpl implements MentorSlotDao {

	@Autowired
	private EntityManager entity;
	
	@SuppressWarnings("unchecked")
	public List<MentorSlot> getAllSlot() {
		
		List<MentorSlot> list = this.entity.createQuery("From MentorSlot").getResultList();
		
		return list;
	}

	public MentorSlot getSlotById(Integer id) {
		
		MentorSlot slot = this.entity.find(MentorSlot.class,id);
		
		return slot;
	}

	public boolean saveSlot(MentorSlot slot) {
		
		this.entity.persist(slot);
		
		return true;
	}

	public boolean updateSlot(MentorSlot slot) {
		
		MentorSlot s = this.entity.find(MentorSlot.class,slot.getId());
		
		s.setMentorId(slot.getMentorId());
		s.setTimeFrom(slot.getTimeFrom());
		s.setTimeTo(slot.getTimeTo());
		
		this.entity.flush();
		
		return true;
	}

	public boolean deleteSlot(Integer id) {

		MentorSlot slot = this.entity.find(MentorSlot.class,id);
		this.entity.remove(slot);
		
		return true;
	}

	@SuppressWarnings("unchecked")
	public List<MentorSlot> getMentorById(Integer id) {
		
		List<MentorSlot> list = this.entity.createQuery("From MentorSlot Where mentorId="+id).getResultList();
		
		return list;
	}

	@Override
	public boolean activeSlot(Integer slotId, Integer activeId) {
		
		MentorSlot s = this.entity.find(MentorSlot.class,slotId);
		
		s.setActive(activeId);
		
		this.entity.flush();
		
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MentorSlot> getSearchMentorById(Integer id) {
		
		List<MentorSlot> list = this.entity.createQuery("From MentorSlot Where mentorId="+id+" And isActive=1").getResultList();
		
		return list;
	}
}
