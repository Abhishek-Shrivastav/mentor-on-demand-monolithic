package com.mentorondemand.entity;

import java.util.List;

public class SlotList {

	private List<MentorSlot> listSlot;
	
	public SlotList() {}

	public SlotList(List<MentorSlot> listSlot) {
		
		this.listSlot = listSlot;
	}

	public List<MentorSlot> getListSlot() {
		return listSlot;
	}
}
