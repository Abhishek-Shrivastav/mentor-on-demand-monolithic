package com.mentorondemand.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mentorondemand.entity.MentorSkill;
import com.mentorondemand.entity.MentorSlot;
import com.mentorondemand.entity.SearchList;
import com.mentorondemand.entity.SearchRequest;
import com.mentorondemand.entity.SkillList;
import com.mentorondemand.entity.SlotList;
import com.mentorondemand.entity.Technology;
import com.mentorondemand.entity.User;

@Service
public class SearchService {

	@Autowired
	private MentorSkillServiceImpl mentorSkill;
	@Autowired
	private TechnologyServiceImpl technology;
	@Autowired
	private UserServiceImpl user;
	@Autowired
	private MentorSlotServiceImpl mentorSlot;
	@Autowired
	private TrainingServiceImpl training;
	
	public SearchList searchById(Integer techId)
	{
		List<SearchRequest> search = new LinkedList<SearchRequest>();
		List<MentorSlot> mentorSlotList = new LinkedList<MentorSlot>();
		
		SkillList skillList = this.mentorSkill.getMentorSkillByTechId(techId);
		
		List<MentorSkill> skills = skillList.getListSkill();
		
		for(MentorSkill skill : skills)
		{
			Technology tech = this.technology.getById(skill.getTechnologyId());
			User user = this.user.getById(skill.getMentorId());
			
			SlotList slotList = this.mentorSlot.getSearchByMentorId(skill.getMentorId());
			
			boolean bool;
			int cnt=0;
			if(slotList.getListSlot().size() != 0)
			{
				for(MentorSlot slot : slotList.getListSlot())
				{
					bool = this.training.checkSlot(slot.getId());
					if(bool)
					{
						if(cnt==1)break;
						
						search.add(new SearchRequest(user.getId(),user.getFirstName(),user.getLastName(),user.getYearOfExp(),user.getLinkedInUrl(),tech.getId(),tech.getTechnologyName(),skill.getId(),skill.getAvgRating(),skill.getToc(),skill.getPrerequisites(),skill.getFee()));
						cnt++;
					}
				}
			}
		}
		
		SearchList searchList = new SearchList(search,mentorSlotList);
		
		return searchList;
	}
	
	public SearchList searchAll()
	{
		List<SearchRequest> search = new LinkedList<SearchRequest>();
		List<MentorSlot> mentorSlotList = new LinkedList<MentorSlot>();
		
		SkillList skillList = this.mentorSkill.getAll();
		
		List<MentorSkill> skills = skillList.getListSkill();
		
		for(MentorSkill skill : skills)
		{
			Technology tech = this.technology.getById(skill.getTechnologyId());
			User user = this.user.getById(skill.getMentorId());
			
			SlotList slotList = this.mentorSlot.getSearchByMentorId(skill.getMentorId());
			
			boolean bool=false;
			int cnt=0;
			
			if(slotList.getListSlot().size() != 0)
			{
				for(MentorSlot slot : slotList.getListSlot())
				{
					bool = this.training.checkSlot(slot.getId());
					if(bool)
					{
						if(cnt==1)break;
						
						search.add(new SearchRequest(user.getId(),user.getFirstName(),user.getLastName(),user.getYearOfExp(),user.getLinkedInUrl(),tech.getId(),tech.getTechnologyName(),skill.getId(),skill.getAvgRating(),skill.getToc(),skill.getPrerequisites(),skill.getFee()));
						cnt++;
					}
				}
			}
		}
		
		SearchList searchList = new SearchList(search,mentorSlotList);
		
		return searchList;
	}
	
	public SearchList searchByMentorTech(Integer mentorId,Integer techId)
	{
		List<SearchRequest> search = new LinkedList<SearchRequest>();
		List<MentorSlot> mentorSlotList = new LinkedList<MentorSlot>();
		
		MentorSkill skillList = this.mentorSkill.getSkillByMentorIdAndTechId(mentorId,techId);
		
		Technology tech = this.technology.getById(skillList.getTechnologyId());
		User user = this.user.getById(skillList.getMentorId());
		
		search.add(new SearchRequest(user.getId(),user.getFirstName(),user.getLastName(),user.getYearOfExp(),user.getLinkedInUrl(),tech.getId(),tech.getTechnologyName(),skillList.getId(),skillList.getAvgRating(),skillList.getToc(),skillList.getPrerequisites(),skillList.getFee()));
		
		SlotList slotList = this.mentorSlot.getSearchByMentorId(skillList.getMentorId());
		
		for(MentorSlot slot : slotList.getListSlot())
		{
			boolean status = this.training.checkSlot(slot.getId());
			
			if(status)
			{
				mentorSlotList.add(new MentorSlot(slot.getId(),slot.getMentorId(),slot.getTimeFrom(),slot.getTimeTo(),slot.getActive()));
			}
		}
		
		SearchList searchList = new SearchList(search,mentorSlotList);
		
		return searchList;
	}
}
