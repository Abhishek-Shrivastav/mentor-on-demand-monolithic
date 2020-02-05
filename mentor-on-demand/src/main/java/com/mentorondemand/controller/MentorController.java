package com.mentorondemand.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
import com.mentorondemand.entity.PaymentList;
import com.mentorondemand.entity.SkillList;
import com.mentorondemand.entity.SlotList;
import com.mentorondemand.entity.Technology;
import com.mentorondemand.entity.TechnologyList;
import com.mentorondemand.entity.Training;
import com.mentorondemand.entity.TrainingData;
import com.mentorondemand.entity.TrainingList;
import com.mentorondemand.entity.User;
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
	
	//logout
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		session.invalidate();

		return "redirect:/signin";
	}
	
	//default home
	@RequestMapping("/home")
	public String mentor(Model model,HttpServletRequest request)
	{
		HttpSession session = request.getSession(false);
		if(session.getAttribute("user") == null)
		{
			return "redirect:/mentor/logout";
		}
		User user = (User) session.getAttribute("user");
		model.addAttribute("roleId",user.getRoleId());
		
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
	public String training(Model model,HttpServletRequest request)
	{
		HttpSession session = request.getSession(false);
		if(session.getAttribute("user") == null)
		{
			return "redirect:/mentor/logout";
		}
		User user = (User) session.getAttribute("user");
		model.addAttribute("roleId",user.getRoleId());
		
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
	public String runningTraining(Model model,HttpServletRequest request)
	{
		HttpSession session = request.getSession(false);
		if(session.getAttribute("user") == null)
		{
			return "redirect:/mentor/logout";
		}
		User user = (User) session.getAttribute("user");
		model.addAttribute("roleId",user.getRoleId());
		
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
	public String completeTraining(Model model,HttpServletRequest request)
	{
		HttpSession session = request.getSession(false);
		if(session.getAttribute("user") == null)
		{
			return "redirect:/mentor/logout";
		}
		User user = (User) session.getAttribute("user");
		model.addAttribute("roleId",user.getRoleId());
		
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
	public String editProfile(Model model,HttpServletRequest request)
	{
		HttpSession session = request.getSession(false);
		if(session.getAttribute("user") == null)
		{
			return "redirect:/mentor/logout";
		}
		User user = (User) session.getAttribute("user");
		model.addAttribute("roleId",user.getRoleId());
		
		model.addAttribute("command",user);
		
		return "edit-profile";
	}
	
	@RequestMapping(value="/update-profile",method = RequestMethod.POST)
	public String updateProfile(@ModelAttribute("user") User user,HttpServletRequest request,Model model)
	{
		HttpSession session = request.getSession(false);
		if(session.getAttribute("user") == null)
		{
			return "redirect:/mentor/logout";
		}
		User user1 = (User) session.getAttribute("user");
		model.addAttribute("roleId",user1.getRoleId());
		
		this.userService.editUser(user);
		
		User user2 = this.userService.getById(user.getId());
		request.getSession().removeAttribute("user");
		request.getSession().setAttribute("user",user2);
		
		return "redirect:/mentor/edit-profile";
	}
	
	//mentor skill section
	@RequestMapping("/view-skill")
	public String skill(Model model,HttpServletRequest request)
	{
		HttpSession session = request.getSession(false);
		if(session.getAttribute("user") == null)
		{
			return "redirect:/mentor/logout";
		}
		User user = (User)session.getAttribute("user");
		model.addAttribute("roleId",user.getRoleId());
		
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
	public String editSkill(@PathVariable("id") Integer editId,Model model,HttpServletRequest request)
	{
		HttpSession session = request.getSession(false);
		if(session.getAttribute("user") == null)
		{
			return "redirect:/mentor/logout";
		}

		User user = (User)session.getAttribute("user");
		model.addAttribute("mentorId",user.getId());
		model.addAttribute("roleId",user.getRoleId());
		
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
		model.addAttribute("command",mentorSkill);
		
		return "edit-skill";
	}
	
	@RequestMapping(value = "/update-skill",method=RequestMethod.POST)
	public String updateSkill(@ModelAttribute("mentoSkill") MentorSkill mentorSkill,Model model,HttpServletRequest request)
	{
		HttpSession session = request.getSession(false);
		if(session.getAttribute("user") == null)
		{
			return "redirect:/mentor/logout";
		}
		User user = (User)session.getAttribute("user");
		model.addAttribute("roleId",user.getRoleId());
		
		boolean status = this.skillService.update(mentorSkill);
		
		if(!status)
			return "redirect:/mentor/view-skill";
		
		return "redirect:/mentor/view-skill";
	}
	
	@RequestMapping(value = "/delete-skill/{id}")
	public String deleteSkill(@PathVariable("id") Integer deleteId,HttpServletRequest request,Model model)
	{
		HttpSession session = request.getSession(false);
		if(session.getAttribute("user") == null)
		{
			return "redirect:/mentor/logout";
		}
		User user = (User)session.getAttribute("user");
		model.addAttribute("roleId",user.getRoleId());
		
		boolean status = this.skillService.delete(deleteId);
		
		if(!status)
			return "redirect:/mentor/view-skill";
		
		return "redirect:/mentor/view-skill";
	}
	
	@RequestMapping(value = "/add-skill")
	public String addSkill(Model model,HttpServletRequest request)
	{
		HttpSession session = request.getSession(false);
		if(session.getAttribute("user") == null)
		{
			return "redirect:/mentor/logout";
		}
		User user = (User)session.getAttribute("user");
		model.addAttribute("mentorId",user.getId());
		model.addAttribute("roleId",user.getRoleId());
		
		SkillList skillList = this.skillService.getMentorSkillById(user.getId());
		
		TechnologyList techList = this.techService.getAll();
		List<Technology> technology = new ArrayList<>();
		
		for(MentorSkill mentorSkill : skillList.getListSkill())
		{
			technology.add(this.techService.getById(mentorSkill.getTechnologyId()));
		}
		techList.getTechList().removeAll(technology);
		
		model.addAttribute("techList",techList.getTechList());
		model.addAttribute("command",new MentorSkill());
		
		return "save-skill";
	}
	
	@RequestMapping(value = "/save-skill",method=RequestMethod.POST)
	public String addSkill(@ModelAttribute("mentoSkill") MentorSkill mentorSkill,Model model,HttpServletRequest request)
	{
		HttpSession session = request.getSession(false);
		if(session.getAttribute("user") == null)
		{
			return "redirect:/mentor/logout";
		}
		User user = (User)session.getAttribute("user");
		model.addAttribute("roleId",user.getRoleId());
		
		boolean status = this.skillService.save(mentorSkill);
		
		if(!status)
			return "redirect:/mentor/add-skill";
		
		return "redirect:/mentor/add-skill";
	}
	
	//mentor slot section
	@RequestMapping("/view-slot")
	public String slot(Model model,HttpServletRequest request)
	{
		HttpSession session = request.getSession(false);
		if(session.getAttribute("user") == null)
		{
			return "redirect:/mentor/logout";
		}
		User user = (User)session.getAttribute("user");
		model.addAttribute("roleId",user.getRoleId());
		
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
	public String addSlot(Model model,HttpServletRequest request)
	{
		HttpSession session = request.getSession(false);
		if(session.getAttribute("user") == null)
		{
			return "redirect:/mentor/logout";
		}
		User user = (User)session.getAttribute("user");
		model.addAttribute("roleId",user.getRoleId());
		
		model.addAttribute("mentorId",user.getId());
		model.addAttribute("command",new MentorSlot());
		
		return "save-slot";
	}
	
	@RequestMapping(value = "/save-slot", method=RequestMethod.POST)
	public String saveSlot(@ModelAttribute("slot")MentorSlot slot,HttpServletRequest request,Model model)
	{
		HttpSession session = request.getSession(false);
		if(session.getAttribute("user") == null)
		{
			return "redirect:/mentor/logout";
		}
		User user = (User)session.getAttribute("user");
		model.addAttribute("roleId",user.getRoleId());
		
		boolean status = this.slotService.save(slot);
		
		if(!status)
			return "redirect:/mentor/add-slot";
		
		return "redirect:/mentor/add-slot";
	}
	
	@RequestMapping("/active/{slotId}/{activeId}")
	public String activeSlot(@PathVariable("slotId") Integer slotId,@PathVariable("activeId") Integer activeId,HttpServletRequest request,Model model)
	{
		HttpSession session = request.getSession(false);
		if(session.getAttribute("user") == null)
		{
			return "redirect:/mentor/logout";
		}
		User user = (User)session.getAttribute("user");
		model.addAttribute("roleId",user.getRoleId());
		
		boolean status = this.slotService.activeSlot(slotId,activeId);
		
		if(!status)
			return "redirect:/mentor/view-slot";
		
		return "redirect:/mentor/view-slot";
	}
	
	@RequestMapping("/delete-slot/{slotId}")
	public String deleteSlot(@PathVariable("slotId") Integer slotId,HttpServletRequest request,Model model)
	{
		HttpSession session = request.getSession(false);
		if(session.getAttribute("user") == null)
		{
			return "redirect:/mentor/logout";
		}
		User user = (User)session.getAttribute("user");
		model.addAttribute("roleId",user.getRoleId());
		
		boolean status = this.slotService.delete(slotId);
		
		if(!status)
			return "redirect:/mentor/view-slot";
		
		return "redirect:/mentor/view-slot";
	}
	
	//request section
	@RequestMapping("/request")
	public String requestTraining(Model model,HttpServletRequest request)
	{
		HttpSession session = request.getSession(false);
		if(session.getAttribute("user") == null)
		{
			return "redirect:/mentor/logout";
		}
		User user = (User)session.getAttribute("user");
		model.addAttribute("roleId",user.getRoleId());
		
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
	public String accept(@PathVariable Integer trainingId,Model model,HttpServletRequest request)
	{
		HttpSession session = request.getSession(false);
		if(session.getAttribute("user") == null)
		{
			return "redirect:/mentor/logout";
		}
		User user = (User)session.getAttribute("user");
		model.addAttribute("roleId",user.getRoleId());
		
		Training training = this.trainingService.getById(trainingId);
		model.addAttribute("command",training);
		
		return "request-accept";
	}
	
	@RequestMapping("/request-accept")
	public String requestAccept(@ModelAttribute("training") Training training,HttpServletRequest request,Model model)
	{
		HttpSession session = request.getSession(false);
		if(session.getAttribute("user") == null)
		{
			return "redirect:/mentor/logout";
		}
		User user = (User)session.getAttribute("user");
		model.addAttribute("roleId",user.getRoleId());
		
		boolean status = this.trainingService.acceptTraining(training);
		
		if(!status)
			return "redirect:/mentor/request";
		
		return "redirect:/mentor/request";
	}
	
	@RequestMapping("/request-decline/{trainingId}")
	public String requestDecline(@PathVariable Integer trainingId,HttpServletRequest request,Model model)
	{
		HttpSession session = request.getSession(false);
		if(session.getAttribute("user") == null)
		{
			return "redirect:/mentor/logout";
		}
		User user = (User)session.getAttribute("user");
		model.addAttribute("roleId",user.getRoleId());

		boolean status = this.trainingService.userRequest(trainingId,1);
		
		if(!status)
			return "redirect:/mentor/request";
		
		return "redirect:/mentor/request";
	}
	
	@RequestMapping("/progress/{trainingId}")
	public String progress(@PathVariable Integer trainingId,Model model,HttpServletRequest request)
	{
		HttpSession session = request.getSession(false);
		if(session.getAttribute("user") == null)
		{
			return "redirect:/mentor/logout";
		}
		User user = (User)session.getAttribute("user");
		model.addAttribute("roleId",user.getRoleId());

		Training training = this.trainingService.getById(trainingId);
		
		if(training.getProgress() == 0 || training.getProgress() == 25 || training.getProgress() == 50 || training.getProgress() == 75)
		{
			training.setProgress(training.getProgress() + 25);
		}
		model.addAttribute("command",training);
		
		return "training-progress";
	}
	
	@RequestMapping("/progerss-upgrade")
	public String progerssUpgrade(@ModelAttribute("training") Training training,HttpServletRequest request,Model model)
	{
		HttpSession session = request.getSession(false);
		if(session.getAttribute("user") == null)
		{
			return "redirect:/mentor/logout";
		}
		User user = (User)session.getAttribute("user");
		model.addAttribute("roleId",user.getRoleId());

		boolean status = this.trainingService.progressUpgrade(training);
		
		if(!status)
			return "redirect:/mentor/running";
		
		return "redirect:/mentor/running";
	}
	
	@RequestMapping("/complete/{trainingId}")
	public String completeTraining(@PathVariable("trainingId") Integer trainingId,HttpServletRequest request,Model model)
	{
		HttpSession session = request.getSession(false);
		if(session.getAttribute("user") == null)
		{
			return "redirect:/mentor/logout";
		}
		User user = (User)session.getAttribute("user");
		model.addAttribute("roleId",user.getRoleId());

		boolean status = this.trainingService.userRequest(trainingId,4);
		
		if(!status)
			return "redirect:/mentor/running";
		
		return "redirect:/mentor/running";
	}
	
	//pay
	@RequestMapping("/payment")
	public String payment(HttpServletRequest request,Model model)
	{
		HttpSession session = request.getSession(false);
		if(session.getAttribute("user") == null)
		{
			return "redirect:/mentor/logout";
		}
		User user = (User)session.getAttribute("user");
		model.addAttribute("roleId",user.getRoleId());
		
		TrainingList trainingList = this.trainingService.getTrainingById(user.getId());
		List<Payment> payList = new ArrayList<>();
		
		for(Training training : trainingList.getTrainingList())
		{
			payList.addAll(this.payService.getPaymentByTraining(training.getId()).getListPayment());
		}
		model.addAttribute("payList",payList);
		
		return "running-payment";
	}
}
