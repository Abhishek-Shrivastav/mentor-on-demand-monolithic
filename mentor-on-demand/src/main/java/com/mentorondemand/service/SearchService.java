package com.mentorondemand.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.mentorondemand.entity.CountTraining;
import com.mentorondemand.entity.CountTrainingObject;
import com.mentorondemand.entity.MentorSkill;
import com.mentorondemand.entity.MentorSlot;
import com.mentorondemand.entity.Search;
import com.mentorondemand.entity.SearchList;
import com.mentorondemand.entity.SkillList;
import com.mentorondemand.entity.SlotList;
import com.mentorondemand.entity.StatusResponse;
import com.mentorondemand.entity.Technology;
import com.mentorondemand.entity.TrainingStatusResponse;
import com.mentorondemand.entity.User;
import com.mentorondemand.service.MentorSkillServiceImpl;
import com.mentorondemand.service.MentorSlotServiceImpl;
import com.mentorondemand.service.TechnologyServiceImpl;
import com.mentorondemand.service.TrainingServiceImpl;
import com.mentorondemand.service.UserServiceImpl;

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
		List<Search> search = new LinkedList<Search>();
		List<CountTraining> count = new LinkedList<CountTraining>();
		
		SkillList skillList = this.mentorSkill.getMentorSkillByTechId(techId);
		
		List<MentorSkill> skills = skillList.getListSkill();

		for(MentorSkill skill : skills) {
			
			Technology tech = this.technology.getById(skill.getTechnologyId());
			User user = this.user.getById(skill.getMentorId());
			SlotList slotList = this.mentorSlot.getByMentorId(skill.getMentorId());
			
			List<MentorSlot> slots = slotList.getListSlot();
			
			for(MentorSlot slot : slots) {
				
				boolean status = this.training.checkSlot(slot.getId());
				
				if(status)
				{
					search.add(new Search(user.getId(),user.getFirstName(),user.getLastName(),user.getYearOfExp(),user.getLinkedInUrl(),tech.getId(),tech.getTechnologyName(),skill.getId(),skill.getAvgRating(),skill.getToc(),skill.getPrerequisites(),skill.getFee(),slot.getId(),slot.getTimeFrom(),slot.getTimeTo()));
				}
			}
		}
		
		SearchList searchList = new SearchList(search,count);
		System.out.println(searchList.getSearchList());
		
		return searchList;
	}
	
	public SearchList searchAll()
	{
		List<Search> search = new LinkedList<Search>();
		List<CountTraining> count = new LinkedList<CountTraining>();
		
		SkillList skillList = this.mentorSkill.getAll();
		
		List<MentorSkill> skills = skillList.getListSkill();

		for(MentorSkill skill : skills) {
			
			Technology tech = this.technology.getById(skill.getTechnologyId());
			User user = this.user.getById(skill.getMentorId());
			SlotList slotList = this.mentorSlot.getSearchByMentorId(skill.getMentorId());
			
			List<MentorSlot> slots = slotList.getListSlot();
			
			for(MentorSlot slot : slots) {
				
				boolean status = this.training.checkSlot(slot.getId());
				
				if(status)
				{
					search.add(new Search(user.getId(),user.getFirstName(),user.getLastName(),user.getYearOfExp(),user.getLinkedInUrl(),tech.getId(),tech.getTechnologyName(),skill.getId(),skill.getAvgRating(),skill.getToc(),skill.getPrerequisites(),skill.getFee(),slot.getId(),slot.getTimeFrom(),slot.getTimeTo()));
				}
			}
		}
		
		SearchList searchList = new SearchList(search,count);
		
		return searchList;
	}
	
	/* Exception handler */
	
	/*@ExceptionHandler
	public ResponseEntity<ExceptionErrorResponse> handler(DataNotFoundException ex)
	{
		ExceptionErrorResponse error = new ExceptionErrorResponse(ex.getMessage(),HttpStatus.NOT_FOUND.value(),System.currentTimeMillis());
		
		ResponseEntity<ExceptionErrorResponse> responseEntity = new ResponseEntity<ExceptionErrorResponse>(error,HttpStatus.NOT_FOUND);
		
		return responseEntity;
	}*/
}
