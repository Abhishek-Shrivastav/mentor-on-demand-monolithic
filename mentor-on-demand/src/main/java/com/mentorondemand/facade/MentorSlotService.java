package com.mentorondemand.facade;

import com.mentorondemand.entity.MentorSlot;
import com.mentorondemand.entity.SlotList;

public interface MentorSlotService {

	//List<MentorSlot> getAll();
	SlotList getAll();
	MentorSlot getById(Integer id);
	boolean save(MentorSlot slot);
	boolean update(MentorSlot slot);
	boolean delete(Integer id);
	SlotList getByMentorId(Integer id);
	boolean activeSlot(Integer slotId,Integer activeId);
	SlotList getSearchByMentorId(Integer id);
}
