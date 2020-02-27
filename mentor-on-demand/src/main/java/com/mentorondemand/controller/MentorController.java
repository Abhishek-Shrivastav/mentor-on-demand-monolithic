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

import com.mentorondemand.entity.MentorSkill;
import com.mentorondemand.entity.MentorSkillData;
import com.mentorondemand.entity.MentorSlot;
import com.mentorondemand.entity.MentorSlotData;
import com.mentorondemand.entity.NotificationData;
import com.mentorondemand.entity.Payment;
import com.mentorondemand.entity.SkillList;
import com.mentorondemand.entity.SlotList;
import com.mentorondemand.entity.Technology;
import com.mentorondemand.entity.TechnologyList;
import com.mentorondemand.entity.Training;
import com.mentorondemand.entity.TrainingData;
import com.mentorondemand.entity.TrainingList;
import com.mentorondemand.entity.User;
import com.mentorondemand.entity.UserProfile;
import com.mentorondemand.exception.GlobalException;
import com.mentorondemand.facade.MentorSkillService;
import com.mentorondemand.facade.MentorSlotService;
import com.mentorondemand.facade.PaymentService;
import com.mentorondemand.facade.TechnologyService;
import com.mentorondemand.facade.TrainingService;
import com.mentorondemand.facade.UserService;

@Controller
@RequestMapping("/mentor")
public class MentorController {

	@Autowired
	private TrainingService trainingService;
	@Autowired
	private UserService userService;
	@Autowired
	private MentorSkillService skillService;
	@Autowired
	private MentorSlotService slotService;
	@Autowired
	private TechnologyService techService;
	@Autowired
	private PaymentService payService;
	
	//default home
	@RequestMapping("/home")
	public String mentor(Model model,Principal principal)
	{
		User user = this.userService.findByUsername(principal.getName());
		model.addAttribute("roleId",user.getRoleId());
		model.addAttribute("firstName",user.getFirstName()+" "+user.getLastName());
		
		TrainingList trainingList = this.trainingService.getTrainingById(user.getId());
		
		List<TrainingData> trainingDataList = new ArrayList<TrainingData>();
		TrainingData trainingData = null;
		
		for(Training training : trainingList.getTrainingList())
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
				action="Accept";
			else if(training.getRequest()==3)
				action="Running";
			else
				action="Completed";
			
			trainingData = new TrainingData(training.getId(), mentorName, userName, slotTimeFrom, slotTimeTo, techName,training.getProgress(),training.getStartDate(),training.getEndDate(),training.getTotalFee(),training.getAmountReceived(),training.getInstallmentStatus(),training.getRating(),action);
			
			trainingDataList.add(trainingData);
		}
		
		model.addAttribute("trainingList",trainingDataList);
		
		//notification bar
		TrainingList notifyList = this.trainingService.getActionTrainingMentorId(user.getId(),0);
		
		List<NotificationData> notificationDataList = new ArrayList<NotificationData>();
		NotificationData notificationData = null;
		
		for(Training training : notifyList.getTrainingList())
		{
			String userName = this.userService.getById(training.getUserId()).getFirstName();
			String techName = this.techService.getById(training.getTechId()).getTechnologyName();
			String request1 = "Propose";
			
			notificationData = new NotificationData(training.getId(),userName,techName,request1);
			notificationDataList.add(notificationData);
		}
		
		model.addAttribute("notifyList",notificationDataList);
		
		return "mentor";
	}
	
	//training section
	@RequestMapping("/training")
	public String training(Model model,Principal principal)
	{
		User user = this.userService.findByUsername(principal.getName());
		model.addAttribute("roleId",user.getRoleId());
		model.addAttribute("firstName",user.getFirstName()+" "+user.getLastName());
		
		TrainingList trainingList = this.trainingService.getTrainingById(user.getId());
		
		List<TrainingData> trainingDataList = new ArrayList<TrainingData>();
		TrainingData trainingData = null;
		
		for(Training training : trainingList.getTrainingList())
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
				action="Accept";
			else if(training.getRequest()==3)
				action="Running";
			else
				action="Completed";
			
			trainingData = new TrainingData(training.getId(), mentorName, userName, slotTimeFrom, slotTimeTo, techName,training.getProgress(),training.getStartDate(),training.getEndDate(),training.getTotalFee(),training.getAmountReceived(),training.getInstallmentStatus(),training.getRating(),action);
			trainingDataList.add(trainingData);
		}
		
		model.addAttribute("trainingList",trainingDataList);
		
		return "training";
	}
	
	@RequestMapping("/running")
	public String runningTraining(Model model,Principal principal)
	{
		User user = this.userService.findByUsername(principal.getName());
		model.addAttribute("roleId",user.getRoleId());
		model.addAttribute("firstName",user.getFirstName()+" "+user.getLastName());
		
		TrainingList trainingList = this.trainingService.getTrainingByUserIdAndRequest(user.getId(),3);	//in dao changed mentorId instad of userId 
		
		List<TrainingData> trainingDataList = new ArrayList<TrainingData>();
		TrainingData trainingData = null;
		
		for(Training training : trainingList.getTrainingList())
		{
			String mentorName = this.userService.getById(training.getMentorId()).getFirstName();
			String userName = this.userService.getById(training.getUserId()).getFirstName();
			String slotTimeFrom = this.slotService.getById(training.getSlotId()).getTimeFrom().toString(); 
			String slotTimeTo = this.slotService.getById(training.getSlotId()).getTimeTo().toString();
			String techName = this.techService.getById(training.getTechId()).getTechnologyName();
			String action = "Running";
			
			
			trainingData = new TrainingData(training.getId(), mentorName, userName, slotTimeFrom, slotTimeTo, techName,training.getProgress(),training.getStartDate(),training.getEndDate(),training.getTotalFee(),training.getAmountReceived(),training.getInstallmentStatus(),training.getRating(),action);
			trainingDataList.add(trainingData);
		}
		
		model.addAttribute("trainingList",trainingDataList);
		
		return "running-training";
	}
	
	@RequestMapping("/complete")
	public String completeTraining(Model model,Principal principal)
	{
		User user = this.userService.findByUsername(principal.getName());
		model.addAttribute("roleId",user.getRoleId());
		model.addAttribute("firstName",user.getFirstName()+" "+user.getLastName());
		
		TrainingList trainingList = this.trainingService.getTrainingByUserIdAndRequest(user.getId(),4);	//in dao changed mentorId instad of userId 
		
		List<TrainingData> trainingDataList = new ArrayList<TrainingData>();
		TrainingData trainingData = null;
		
		for(Training training : trainingList.getTrainingList())
		{
			String mentorName = this.userService.getById(training.getMentorId()).getFirstName();
			String userName = this.userService.getById(training.getUserId()).getFirstName();
			String slotTimeFrom = this.slotService.getById(training.getSlotId()).getTimeFrom().toString(); 
			String slotTimeTo = this.slotService.getById(training.getSlotId()).getTimeTo().toString();
			String techName = this.techService.getById(training.getTechId()).getTechnologyName();
			String action = "Completed";
			
			trainingData = new TrainingData(training.getId(), mentorName, userName, slotTimeFrom, slotTimeTo, techName,training.getProgress(),training.getStartDate(),training.getEndDate(),training.getTotalFee(),training.getAmountReceived(),training.getInstallmentStatus(),training.getRating(),action);
			trainingDataList.add(trainingData);
		}
		
		model.addAttribute("trainingList",trainingDataList);
		
		return "complete-training";
	}
	
	//edit profile
	@RequestMapping("/edit-profile")
	public String editProfile(Model model,Principal principal)
	{
		User user = this.userService.findByUsername(principal.getName());
		model.addAttribute("roleId",user.getRoleId());
		model.addAttribute("firstName",user.getFirstName()+" "+user.getLastName());
		
		model.addAttribute("users",user);
		
		return "edit-profile";
	}
	
	@RequestMapping(value="/update-profile",method = RequestMethod.POST)
	public String updateProfile(@Valid @ModelAttribute("users") UserProfile user,BindingResult result,Model model,Principal principal)
	{
		User user1 = this.userService.findByUsername(principal.getName());
		model.addAttribute("roleId",user1.getRoleId());
		model.addAttribute("firstName",user1.getFirstName()+" "+user1.getLastName());
		
		if(result.hasErrors())
		{
			return "edit-profile";
		}
		
		User user2 = this.userService.getById(user.getId());
		user2.setFirstName(user.getFirstName());
		user2.setLastName(user.getLastName());
		user2.setContact(user.getContact());
		user2.setLinkedInUrl(user.getLinkedInUrl());
		user2.setYearOfExp(user.getYearOfExp());
		
		boolean status = this.userService.editUser(user2);
		
		if(!status)
			throw new GlobalException("Profile updation failed!");
		
		return "redirect:/mentor/edit-profile";
	}
	
	//mentor skill section
	@RequestMapping("/view-skill")
	public String skill(Model model,Principal principal)
	{
		User user = this.userService.findByUsername(principal.getName());
		model.addAttribute("roleId",user.getRoleId());
		model.addAttribute("firstName",user.getFirstName()+" "+user.getLastName());
		
		List<MentorSkillData> skillDataList = new ArrayList<>();
		MentorSkillData skillData = null;
		
		SkillList skillList = this.skillService.getMentorSkillById(user.getId());
		
		for(MentorSkill mentorSkill : skillList.getListSkill())
		{
			String mentorName = this.userService.getById(mentorSkill.getMentorId()).getFirstName()+" ";
			mentorName += this.userService.getById(mentorSkill.getMentorId()).getLastName();
			String technologyName = this.techService.getById(mentorSkill.getTechnologyId()).getTechnologyName();
			
			skillData = new MentorSkillData(mentorSkill.getId(),mentorName,technologyName,mentorSkill.getAvgRating(),mentorSkill.getToc(),mentorSkill.getPrerequisites(),mentorSkill.getFee());
			skillDataList.add(skillData);
		}
		
		model.addAttribute("skillList",skillDataList);
		
		return "view-skill";
	}
	
	@RequestMapping(value = "/edit-skill/{id}")
	public String editSkill(@PathVariable("id") Integer editId,Model model,Principal principal)
	{
		User user = this.userService.findByUsername(principal.getName());
		model.addAttribute("mentorId",user.getId());
		model.addAttribute("roleId",user.getRoleId());
		model.addAttribute("firstName",user.getFirstName()+" "+user.getLastName());
		
		SkillList skillList = this.skillService.getMentorSkillById(user.getId());
		
		TechnologyList techList = this.techService.getAll();
		List<Technology> technology = new ArrayList<>();
		
		for(MentorSkill mentorSkill : skillList.getListSkill())
		{
			technology.add(this.techService.getById(mentorSkill.getTechnologyId()));
		}
		techList.getTechList().removeAll(technology);
		
		model.addAttribute("techList",techList.getTechList());
		
		MentorSkill mentorSkill = this.skillService.getById(editId);
		model.addAttribute("mentorId",mentorSkill.getMentorId());
		model.addAttribute("techId",mentorSkill.getTechnologyId());
		model.addAttribute("techName",this.techService.getById(mentorSkill.getTechnologyId()).getTechnologyName());
		model.addAttribute("mentoSkill",mentorSkill);
		
		return "edit-skill";
	}
	
	@RequestMapping(value = "/update-skill",method=RequestMethod.POST)
	public String updateSkill(@Valid @ModelAttribute("mentoSkill") MentorSkill mentorSkill,BindingResult result,Model model,Principal principal)
	{
		User user = this.userService.findByUsername(principal.getName());
		model.addAttribute("roleId",user.getRoleId());
		model.addAttribute("firstName",user.getFirstName()+" "+user.getLastName());
		
		if(result.hasErrors())
		{
			SkillList skillList = this.skillService.getMentorSkillById(user.getId());
			
			TechnologyList techList = this.techService.getAll();
			List<Technology> technology = new ArrayList<>();
			
			for(MentorSkill skill : skillList.getListSkill())
			{
				technology.add(this.techService.getById(skill.getTechnologyId()));
			}
			techList.getTechList().removeAll(technology);
			
			model.addAttribute("mentorId",user.getId());
			model.addAttribute("techList",techList.getTechList());
			model.addAttribute("techId",mentorSkill.getTechnologyId());
			model.addAttribute("techName",this.techService.getById(mentorSkill.getTechnologyId()).getTechnologyName());
			
			return "edit-skill";
		}
		
		boolean status = this.skillService.update(mentorSkill);
		
		if(!status)
			throw new GlobalException("Skill updation failed!");
		
		return "redirect:/mentor/view-skill";
	}
	
	@RequestMapping(value = "/delete-skill/{id}")
	public String deleteSkill(@PathVariable("id") Integer deleteId,Model model,Principal principal)
	{
		User user = this.userService.findByUsername(principal.getName());
		model.addAttribute("roleId",user.getRoleId());
		model.addAttribute("firstName",user.getFirstName()+" "+user.getLastName());
		
		boolean status = this.skillService.delete(deleteId);
		
		if(!status)
			throw new GlobalException("Skill deletion failed!");
		
		return "redirect:/mentor/view-skill";
	}
	
	@RequestMapping(value = "/add-skill")
	public String addSkill(Model model,Principal principal)
	{
		User user = this.userService.findByUsername(principal.getName());
		model.addAttribute("mentorId",user.getId());
		model.addAttribute("roleId",user.getRoleId());
		model.addAttribute("firstName",user.getFirstName()+" "+user.getLastName());
		
		SkillList skillList = this.skillService.getMentorSkillById(user.getId());
		
		TechnologyList techList = this.techService.getAll();
		List<Technology> technology = new ArrayList<>();
		
		for(MentorSkill mentorSkill : skillList.getListSkill())
		{
			technology.add(this.techService.getById(mentorSkill.getTechnologyId()));
		}
		techList.getTechList().removeAll(technology);
		
		model.addAttribute("techList",techList.getTechList());
		model.addAttribute("mentoSkill",new MentorSkill());
		
		return "save-skill";
	}
	
	@RequestMapping(value = "/save-skill",method=RequestMethod.POST)
	public String addSkill(@Valid @ModelAttribute("mentoSkill") MentorSkill mentorSkill,BindingResult result,Model model,Principal principal)
	{
		User user = this.userService.findByUsername(principal.getName());
		model.addAttribute("roleId",user.getRoleId());
		model.addAttribute("firstName",user.getFirstName()+" "+user.getLastName());
		
		if(result.hasErrors())
		{
			SkillList skillList = this.skillService.getMentorSkillById(user.getId());
			
			TechnologyList techList = this.techService.getAll();
			List<Technology> technology = new ArrayList<>();
			
			for(MentorSkill skill : skillList.getListSkill())
			{
				technology.add(this.techService.getById(skill.getTechnologyId()));
			}
			techList.getTechList().removeAll(technology);
			
			model.addAttribute("mentorId",user.getId());
			model.addAttribute("techList",techList.getTechList());
			
			return "save-skill";
		}
		
		boolean status = this.skillService.save(mentorSkill);
		
		if(!status)
			throw new GlobalException("Skill insertion failed!");
		
		return "redirect:/mentor/add-skill";
	}
	
	//mentor slot section
	@RequestMapping("/view-slot")
	public String slot(Model model,Principal principal)
	{
		User user = this.userService.findByUsername(principal.getName());
		model.addAttribute("roleId",user.getRoleId());
		model.addAttribute("firstName",user.getFirstName()+" "+user.getLastName());
		
		List<MentorSlotData> slotDataList = new ArrayList<>();
		MentorSlotData slotData = null;
		
		SlotList slotList = this.slotService.getByMentorId(user.getId());
		boolean bool = false;
		
		for(MentorSlot slot : slotList.getListSlot())
		{
			bool = this.trainingService.checkSlot(slot.getId());
			String status = null;
			String active = null;
			
			if(bool)
				status = "Public";
			else
				status = "Protected";
			
			if(slot.getActive() == 1)
				active = "Active";
			else
				active = "InActive";
			
			slotData = new MentorSlotData(slot.getId(),slot.getMentorId(),slot.getTimeFrom(),slot.getTimeTo(),status,active);
			slotDataList.add(slotData);
		}
		
		model.addAttribute("slotList",slotDataList);
		
		return "view-slot";
	}
	
	@RequestMapping("/add-slot")
	public String addSlot(Model model,Principal principal)
	{
		User user = this.userService.findByUsername(principal.getName());
		model.addAttribute("roleId",user.getRoleId());
		model.addAttribute("firstName",user.getFirstName()+" "+user.getLastName());
		
		model.addAttribute("mentorId",user.getId());
		model.addAttribute("slot",new MentorSlot());
		
		return "save-slot";
	}
	
	@RequestMapping(value = "/save-slot", method=RequestMethod.POST)
	public String saveSlot(@ModelAttribute("slot") MentorSlot slot,Model model,Principal principal)
	{
		User user = this.userService.findByUsername(principal.getName());
		model.addAttribute("roleId",user.getRoleId());
		model.addAttribute("firstName",user.getFirstName()+" "+user.getLastName());
		
		boolean status = this.slotService.save(slot);
		
		if(!status)
			throw new GlobalException("Slot insertion failed!");
		
		return "redirect:/mentor/add-slot";
	}
	
	@RequestMapping("/active/{slotId}/{activeId}")
	public String activeSlot(@PathVariable("slotId") Integer slotId,@PathVariable("activeId") Integer activeId,Model model,Principal principal)
	{
		User user = this.userService.findByUsername(principal.getName());
		model.addAttribute("roleId",user.getRoleId());
		model.addAttribute("firstName",user.getFirstName()+" "+user.getLastName());
		
		boolean status = this.slotService.activeSlot(slotId,activeId);
		
		if(!status)
			throw new GlobalException("Slot activation failed!");
		
		return "redirect:/mentor/view-slot";
	}
	
	@RequestMapping("/delete-slot/{slotId}")
	public String deleteSlot(@PathVariable("slotId") Integer slotId,Model model,Principal principal)
	{
		User user = this.userService.findByUsername(principal.getName());
		model.addAttribute("roleId",user.getRoleId());
		model.addAttribute("firstName",user.getFirstName()+" "+user.getLastName());
		
		boolean status = this.slotService.delete(slotId);
		
		if(!status)
			throw new GlobalException("Slot deletion failed!");
		
		return "redirect:/mentor/view-slot";
	}
	
	//request section
	@RequestMapping("/request")
	public String requestTraining(Model model,Principal principal)
	{
		User user = this.userService.findByUsername(principal.getName());
		model.addAttribute("roleId",user.getRoleId());
		model.addAttribute("firstName",user.getFirstName()+" "+user.getLastName());
		
		TrainingList trainingList = this.trainingService.getActionTrainingMentorId(user.getId(),0);
		
		List<TrainingData> trainingDataList = new ArrayList<TrainingData>();
		TrainingData trainingData = null;
		
		for(Training training : trainingList.getTrainingList())
		{
			String mentorName = this.userService.getById(training.getMentorId()).getFirstName();
			String userName = this.userService.getById(training.getUserId()).getFirstName();
			String slotTimeFrom = this.slotService.getById(training.getSlotId()).getTimeFrom().toString(); 
			String slotTimeTo = this.slotService.getById(training.getSlotId()).getTimeTo().toString();
			String techName = this.techService.getById(training.getTechId()).getTechnologyName();
			String action = "Propose";
			
			trainingData = new TrainingData(training.getId(), mentorName, userName, slotTimeFrom, slotTimeTo, techName,training.getProgress(),training.getStartDate(),training.getEndDate(),training.getTotalFee(),training.getAmountReceived(),training.getInstallmentStatus(),training.getRating(),action);
			trainingDataList.add(trainingData);
		}
		
		model.addAttribute("trainingList",trainingDataList);
		
		return "request-training";
	}
	
	@RequestMapping("/accept/{trainingId}")
	public String accept(@PathVariable Integer trainingId,Model model,Principal principal)
	{
		User user = this.userService.findByUsername(principal.getName());
		model.addAttribute("roleId",user.getRoleId());
		model.addAttribute("firstName",user.getFirstName()+" "+user.getLastName());
		
		Training training = this.trainingService.getById(trainingId);
		model.addAttribute("command",training);
		
		return "request-accept";
	}
	
	@RequestMapping("/request-accept")
	public String requestAccept(@ModelAttribute("training") Training training,Model model,Principal principal)
	{
		User user = this.userService.findByUsername(principal.getName());
		model.addAttribute("roleId",user.getRoleId());
		model.addAttribute("firstName",user.getFirstName()+" "+user.getLastName());
		
		boolean status = this.trainingService.acceptTraining(training);
		
		if(!status)
			throw new GlobalException("Request acceptation failed!");
		
		return "redirect:/mentor/request";
	}
	
	@RequestMapping("/request-decline/{trainingId}")
	public String requestDecline(@PathVariable Integer trainingId,Model model,Principal principal)
	{
		User user = this.userService.findByUsername(principal.getName());
		model.addAttribute("roleId",user.getRoleId());
		model.addAttribute("firstName",user.getFirstName()+" "+user.getLastName());

		boolean status = this.trainingService.userRequest(trainingId,1);
		
		if(!status)
			throw new GlobalException("Request declination failed!");
		
		return "redirect:/mentor/request";
	}
	
	@RequestMapping("/progress/{trainingId}")
	public String progress(@PathVariable Integer trainingId,Model model,Principal principal)
	{
		User user = this.userService.findByUsername(principal.getName());
		model.addAttribute("roleId",user.getRoleId());
		model.addAttribute("firstName",user.getFirstName()+" "+user.getLastName());

		Training training = this.trainingService.getById(trainingId);
		
		if(training.getProgress() == 0 || training.getProgress() == 25 || training.getProgress() == 50 || training.getProgress() == 75)
		{
			training.setProgress(training.getProgress() + 25);
		}
		model.addAttribute("command",training);
		
		return "training-progress";
	}
	
	@RequestMapping("/progerss-upgrade")
	public String progerssUpgrade(@ModelAttribute("training") Training training,Model model,Principal principal)
	{
		User user = this.userService.findByUsername(principal.getName());
		model.addAttribute("roleId",user.getRoleId());
		model.addAttribute("firstName",user.getFirstName()+" "+user.getLastName());

		boolean status = this.trainingService.progressUpgrade(training);
		
		if(!status)
			throw new GlobalException("Progress upgradation failed!");
		
		return "redirect:/mentor/running";
	}
	
	@RequestMapping("/complete/{trainingId}")
	public String completeTraining(@PathVariable("trainingId") Integer trainingId,Model model,Principal principal)
	{
		User user = this.userService.findByUsername(principal.getName());
		model.addAttribute("roleId",user.getRoleId());
		model.addAttribute("firstName",user.getFirstName()+" "+user.getLastName());

		boolean status = this.trainingService.userRequest(trainingId,4);
		
		if(!status)
			throw new GlobalException("Training complete status updation failed!");
		
		return "redirect:/mentor/running";
	}
	
	//pay
	@RequestMapping("/payment")
	public String payment(Model model,Principal principal)
	{
		User user = this.userService.findByUsername(principal.getName());
		model.addAttribute("roleId",user.getRoleId());
		model.addAttribute("firstName",user.getFirstName()+" "+user.getLastName());
		
		TrainingList trainingList = this.trainingService.getTrainingById(user.getId());
		List<Payment> payList = new ArrayList<>();
		
		for(Training training : trainingList.getTrainingList())
		{
			payList.addAll(this.payService.getPaymentByTraining(training.getId()).getListPayment());
		}
		model.addAttribute("payList",payList);
		
		return "running-payment";
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
