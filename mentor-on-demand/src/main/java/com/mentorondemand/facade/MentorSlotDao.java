package com.mentorondemand.facade;

import java.util.List;

import com.mentorondemand.entity.MentorSlot;

public interface MentorSlotDao {

	List<MentorSlot> getAllSlot();
	MentorSlot getSlotById(Integer id);
	boolean saveSlot(MentorSlot slot);
	boolean updateSlot(MentorSlot slot);
	boolean deleteSlot(Integer id);
	List<MentorSlot> getMentorById(Integer id);
	boolean activeSlot(Integer slotId,Integer activeId);
	List<MentorSlot> getSearchMentorById(Integer id);
}
