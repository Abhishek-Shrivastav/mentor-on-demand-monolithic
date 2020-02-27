package com.mentorondemand.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.mentorondemand.entity.MentorSkill;
import com.mentorondemand.entity.NotificationData;
import com.mentorondemand.entity.Technology;
import com.mentorondemand.entity.Training;
import com.mentorondemand.entity.TrainingData;
import com.mentorondemand.entity.TrainingList;
import com.mentorondemand.entity.User;
import com.mentorondemand.entity.UserProfile;
import com.mentorondemand.exception.GlobalException;
import com.mentorondemand.facade.MentorSkillService;
import com.mentorondemand.facade.MentorSlotService;
import com.mentorondemand.facade.TechnologyService;
import com.mentorondemand.facade.TrainingService;
import com.mentorondemand.facade.UserService;
import com.mentorondemand.service.SearchService;

@Controller
@RequestMapping("/student")
public class StudentController {

	@Autowired
	private SearchService search;
	@Autowired
	private TrainingService trainingService;
	@Autowired
	private UserService userService;
	@Autowired
	private MentorSlotService slotService;
	@Autowired
	private TechnologyService techService;
	@Autowired
	private MentorSkillService skillService;
	
	//home search section
	@RequestMapping("/home")
	public String searchAll(Model model,Principal principal)
	{
		User user = this.userService.findByUsername(principal.getName());
		model.addAttribute("roleId",user.getRoleId());
		model.addAttribute("userId",user.getId());
		model.addAttribute("firstName",user.getFirstName()+" "+user.getLastName());
		
		List<Integer> techSkillList = this.skillService.getAllUniqueSkill();
		
		List<Technology> technology = new ArrayList<>();
		
		for(Integer tId : techSkillList)
		{
			technology.add(this.techService.getById(tId));
		}
		
		model.addAttribute("techList",technology);
		model.addAttribute("search",this.search.searchAll().getSearchList());
		
		//notification bar
		TrainingList notifyList = this.trainingService.getActionTraining(user.getId());
		
		List<NotificationData> notificationDataList = new ArrayList<NotificationData>();
		NotificationData notificationData = null;
		
		for(Training training : notifyList.getTrainingList())
		{
			String userName = this.userService.getById(training.getMentorId()).getFirstName();
			String techName = this.techService.getById(training.getTechId()).getTechnologyName();
			
			String request1 = null;
			if(training.getRequest() == 2)
			{
				request1 = "2";
			}
			else
			if(training.getRequest() == 1)
			{
				request1 = "1";
			}
			
			notificationData = new NotificationData(training.getId(),userName,techName,request1);
			notificationDataList.add(notificationData);
		}
		model.addAttribute("notifyList",notificationDataList);

		return "index";
    }
	
	@RequestMapping(value="/search",method=RequestMethod.GET)
	public String searchById(Model model,@RequestParam("techId") String techId,Principal principal)
	{
		User user = this.userService.findByUsername(principal.getName());
		model.addAttribute("roleId",user.getRoleId());
		model.addAttribute("userId",user.getId());
		model.addAttribute("firstName",user.getFirstName()+" "+user.getLastName());
		
		List<Integer> techSkillList = this.skillService.getAllUniqueSkill();
		
		List<Technology> technology = new ArrayList<>();
		
		for(Integer tId : techSkillList)
		{
			technology.add(this.techService.getById(tId));
		}
		
		model.addAttribute("techList",technology);
		model.addAttribute("search",this.search.searchById(Integer.parseInt(techId)).getSearchList());

		return "index";
    }
	
	//send proposal
	@RequestMapping("/view-calendar/{mentorId}/{userId}/{techId}")
	public String viewCalendar(@PathVariable("mentorId") Integer mentorId,@PathVariable("userId") Integer userId,@PathVariable("techId") Integer techId,Model model,Principal principal)
	{
		User user = this.userService.findByUsername(principal.getName());
		model.addAttribute("roleId",user.getRoleId());
		model.addAttribute("firstName",user.getFirstName()+" "+user.getLastName());
		
		model.addAttribute("search",this.search.searchByMentorTech(mentorId,techId).getSearchList());
		model.addAttribute("mentorSlotList",this.search.searchByMentorTech(mentorId,techId).getMentorSlotList());
		model.addAttribute("techId",techId);
		
		return "view-calendar";
	}
	
	@RequestMapping("/send-proposal/{mentorId}/{userId}/{slotId}/{techId}")
	public String sendProposal(@PathVariable("mentorId") Integer mentorId,@PathVariable("userId") Integer userId,@PathVariable("slotId") Integer slotId,@PathVariable("techId") Integer techId,Model model,Principal principal)
	{
		User user = this.userService.findByUsername(principal.getName());
		model.addAttribute("roleId",user.getRoleId());
		model.addAttribute("firstName",user.getFirstName()+" "+user.getLastName());
		
		Training training = new Training(null,mentorId,userId,slotId,techId,0,null,null,0.0,0.0,0,5,0,1);
		
		boolean status = this.trainingService.save(training);
		
		if(!status)
			throw new GlobalException("Proposal sending failed!");
		
		return "redirect:/student/training";
	}
	
	//edit profile
	@RequestMapping("/edit-profile")
	public String editProfile(Model model,Principal principal)
	{
		User user = this.userService.findByUsername(principal.getName());
		model.addAttribute("roleId",user.getRoleId());
		model.addAttribute("firstName",user.getFirstName()+" "+user.getLastName());
		
		model.addAttribute("users",user);
		
		return "user-edit-profile";
	}
	
	@RequestMapping(value="/update-profile",method = RequestMethod.POST)
	public String updateProfile(@Valid @ModelAttribute("users") UserProfile user,BindingResult result,Model model,Principal principal)
	{
		User user1 = this.userService.findByUsername(principal.getName());
		model.addAttribute("roleId",user1.getRoleId());
		model.addAttribute("firstName",user1.getFirstName()+" "+user1.getLastName());
		
		if(result.hasErrors())
		{
			return "user-edit-profile";
		}
		
		User user2 = this.userService.getById(user.getId());
		user2.setFirstName(user.getFirstName());
		user2.setLastName(user.getLastName());
		user2.setContact(user.getContact());
		user2.setLinkedInUrl(user.getLinkedInUrl());
		
		boolean status = this.userService.editUser(user2);
		
		if(!status)
			throw new GlobalException("Profile updation failed!");
		
		return "redirect:/student/edit-profile";
	}
	
	//training section
	@RequestMapping("/training")
	public String training(Model model,Principal principal)
	{
		User user = this.userService.findByUsername(principal.getName());
		model.addAttribute("roleId",user.getRoleId());
		model.addAttribute("firstName",user.getFirstName()+" "+user.getLastName());
		
		Integer userId = user.getId();
		TrainingList list = this.trainingService.getTrainingByUserId(userId);
		List<TrainingData> trainingList = new ArrayList<>();
		
		for(Training training : list.getTrainingList())
		{
			String mentorName = this.userService.getById(training.getMentorId()).getFirstName();
			String userName = this.userService.getById(training.getUserId()).getFirstName();
			String slotTimeFrom = this.slotService.getById(training.getSlotId()).getTimeFrom().toString(); 
			String slotTimeTo = this.slotService.getById(training.getSlotId()).getTimeTo().toString();
			String techName = this.techService.getById(training.getTechId()).getTechnologyName();
			String action = null;
			
			if(training.getRequest()==0)
				action="Request";
			else if(training.getRequest()==1)
				action="Decline";
			else if(training.getRequest()==2)
				action="Accept & Pay";
			else if(training.getRequest()==3)
				action="Running";
			else
				action="Completed";
			
			trainingList.add(new TrainingData(training.getId(), mentorName, userName, slotTimeFrom, slotTimeTo, techName,training.getProgress(),training.getStartDate(),training.getEndDate(),training.getTotalFee(),training.getAmountReceived(),training.getInstallmentStatus(),training.getRating(),action));
			
		}
		model.addAttribute("trainingList",trainingList);
		
		return "training";
	}
	
	//payment section
	@RequestMapping("/pay/{trainingId}")
	public String payment(@PathVariable("trainingId") Integer trainingId,Model model,Principal principal)
	{
		User user = this.userService.findByUsername(principal.getName());
		model.addAttribute("roleId",user.getRoleId());
		model.addAttribute("firstName",user.getFirstName()+" "+user.getLastName());
		
		Training training = this.trainingService.getById(trainingId);
		MentorSkill skill = this.skillService.getSkillByMentorIdAndTechId(training.getMentorId(),training.getTechId());
		String mantor = this.userService.getById(training.getMentorId()).getFirstName();
		
		model.addAttribute("mantor",mantor);
		model.addAttribute("trainingFee",skill.getFee());
		model.addAttribute("training",training);
		
		return "payment-fee";
	}
	
	@RequestMapping(value = "/paypal",method = RequestMethod.POST)
	public String paypal(@ModelAttribute("training") Training training,Model model,Principal principal)
	{
		User user = this.userService.findByUsername(principal.getName());
		model.addAttribute("roleId",user.getRoleId());
		model.addAttribute("firstName",user.getFirstName()+" "+user.getLastName());
		
		Training training1 = this.trainingService.getById(training.getId());
		training1.setTotalFee(training.getTotalFee());
		training1.setRequest(3);
		
		boolean status = this.trainingService.update(training1);
		
		if(!status)
			throw new GlobalException("Payment failed!");
		
		return "redirect:/student/training";
	}
	
	//rating section
	@RequestMapping(value = "/rating/{editId}/{rating}",method = RequestMethod.GET)
	public String rating(@PathVariable("editId") Integer id,@PathVariable("rating") Integer rating,Model model,Principal principal)
	{
		User user = this.userService.findByUsername(principal.getName());
		model.addAttribute("roleId",user.getRoleId());
		model.addAttribute("firstName",user.getFirstName()+" "+user.getLastName());
		
		Training training = this.trainingService.getById(id);
		training.setRating(rating);
		
		boolean status = this.trainingService.update(training);
		
		int mentorId = this.trainingService.getById(id).getMentorId();
		int techId = this.trainingService.getById(id).getTechId();
		
		double avgRating = this.trainingService.getAvgRatingByMentorIdTechId(mentorId,techId);
		
		MentorSkill skill = this.skillService.getSkillByMentorIdAndTechId(mentorId, techId);
		skill.setAvgRating(avgRating);
		
		boolean status1 = this.skillService.update(skill);
		
		if(!status && !status1)
			throw new GlobalException("Rating updation failed!");
		
		return "redirect:/student/training";
	}
	
	//exception handling
	@ExceptionHandler
	public String handleGlobalException(GlobalException ex,Model model)
	{
		model.addAttribute("exception",ex);
		
		return "error";
	}
	
	@ExceptionHandler
	public String handleException(Exception ex, Model model)
	{
		model.addAttribute("exception", ex);
		
		return "error";
	}
	
	//trimmer intercept request
	@InitBinder
	public void interceptRequest(WebDataBinder binder)
	{
		StringTrimmerEditor trimmer = new StringTrimmerEditor(true);
		binder.registerCustomEditor(String.class, trimmer);
	}
}
