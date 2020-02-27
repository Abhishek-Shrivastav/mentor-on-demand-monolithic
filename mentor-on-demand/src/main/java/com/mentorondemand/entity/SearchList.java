package com.mentorondemand.entity;

import java.util.List;

public class SearchList {

	private List<SearchRequest> searchList;
	private List<MentorSlot> mentorSlotList;

	public SearchList() {}

	public SearchList(List<SearchRequest> searchList,List<MentorSlot> mentorSlotList) {
		
		this.searchList = searchList;
		this.mentorSlotList = mentorSlotList;
	}

	public List<SearchRequest> getSearchList() {
		return searchList;
	}

	public List<MentorSlot> getMentorSlotList() {
		return mentorSlotList;
	}
}
