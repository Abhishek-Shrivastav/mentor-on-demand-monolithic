package com.mentorondemand.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mentorondemand.entity.MentorSlot;
import com.mentorondemand.entity.SlotList;
import com.mentorondemand.facade.MentorSlotDao;
import com.mentorondemand.facade.MentorSlotService;

@Service
public class MentorSlotServiceImpl implements MentorSlotService {

	@Autowired
	private MentorSlotDao dao;
	
	public SlotList getAll() {
		
		SlotList list = new SlotList(this.dao.getAllSlot());
		
		return list;
	}

	public MentorSlot getById(Integer id) {
		
		return this.dao.getSlotById(id);
	}

	public boolean save(MentorSlot slot) {
		
		return this.dao.saveSlot(slot);
	}

	public boolean update(MentorSlot slot) {
		
		return this.dao.updateSlot(slot);
	}

	public boolean delete(Integer id) {
		
		return this.dao.deleteSlot(id);
	}

	public SlotList getByMentorId(Integer id) {
		
		SlotList list = new SlotList(this.dao.getMentorById(id));
		
		return list;
	}

	@Override
	public boolean activeSlot(Integer slotId, Integer activeId) {
		
		return this.dao.activeSlot(slotId, activeId);
	}

	@Override
	public SlotList getSearchByMentorId(Integer id) {
		
		SlotList list = new SlotList(this.dao.getSearchMentorById(id));
		
		return list;
	}
}
