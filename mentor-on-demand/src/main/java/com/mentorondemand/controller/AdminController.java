package com.mentorondemand.controller;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

import com.mentorondemand.entity.Payment;
import com.mentorondemand.entity.PaymentList;
import com.mentorondemand.entity.Technology;
import com.mentorondemand.entity.TechnologyList;
import com.mentorondemand.entity.Training;
import com.mentorondemand.entity.TrainingData;
import com.mentorondemand.entity.TrainingList;
import com.mentorondemand.entity.User;
import com.mentorondemand.entity.UserList;
import com.mentorondemand.entity.UserProfile;
import com.mentorondemand.exception.GlobalException;
import com.mentorondemand.facade.MentorSlotService;
import com.mentorondemand.facade.PaymentService;
import com.mentorondemand.facade.TechnologyService;
import com.mentorondemand.facade.TrainingService;
import com.mentorondemand.facade.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private TrainingService trainingService;
	@Autowired
	private UserService userService;
	@Autowired
	private MentorSlotService slotService;
	@Autowired
	private TechnologyService techService;
	@Autowired
	private PaymentService payService;
	
	//default home
	@RequestMapping("/home")
	public String home(Model model,Principal principal)
	{
		User user = this.userService.findByUsername(principal.getName());
		model.addAttribute("roleId",user.getRoleId());
		model.addAttribute("firstName",user.getFirstName()+" "+user.getLastName());
		
		TrainingList trainingList = this.trainingService.getPaymentInstallment();
		
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
		
		return "admin";
	}
	
	//edit profile
	@RequestMapping("/edit-profile")
	public String editProfile(Model model,Principal principal)
	{
		User user = this.userService.findByUsername(principal.getName());
		model.addAttribute("roleId",user.getRoleId());
		model.addAttribute("users",user);
		model.addAttribute("firstName",user.getFirstName()+" "+user.getLastName());
		
		return "admin-edit-profile";
	}
	
	@RequestMapping(value="/update-profile",method = RequestMethod.POST)
	public String updateProfile(@Valid @ModelAttribute("users") UserProfile user,BindingResult result,Model model,Principal principal)
	{
		User user1 = this.userService.findByUsername(principal.getName());
		model.addAttribute("roleId",user1.getRoleId());
		model.addAttribute("firstName",user1.getFirstName()+" "+user1.getLastName());
		
		if(result.hasErrors())
		{
			return "admin-edit-profile";
		}
		
		User user2 = this.userService.getById(user.getId());
		user2.setFirstName(user.getFirstName());
		user2.setLastName(user.getLastName());
		user2.setContact(user.getContact());
		user2.setLinkedInUrl(user.getLinkedInUrl());
		
		boolean status = this.userService.editUser(user2);
		
		if(!status)
			throw new GlobalException("Profile updation failed!");
		
		return "redirect:/admin/edit-profile";
	}
	
	//training section
	@RequestMapping("/training")
	public String training(Model model,Principal principal)
	{
		User user = this.userService.findByUsername(principal.getName());
		model.addAttribute("roleId",user.getRoleId());
		model.addAttribute("firstName",user.getFirstName()+" "+user.getLastName());
		
		TrainingList trainingList = this.trainingService.getAll();
		
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
		
		TrainingList trainingList = this.trainingService.getRunningTraining(3);
		
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
		
		TrainingList trainingList = this.trainingService.getRunningTraining(4);
		
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
	
	//technology section
	@RequestMapping("/view-tech")
	public String viewTech(Model model,Principal principal)
	{
		User user = this.userService.findByUsername(principal.getName());
		model.addAttribute("roleId",user.getRoleId());
		model.addAttribute("firstName",user.getFirstName()+" "+user.getLastName());
		
		TechnologyList techList = this.techService.getAll();
		model.addAttribute("techList",techList.getTechList());
		
		return "view-tech";
	}
	
	@RequestMapping("/add-tech")
	public String addTech(Model model,Principal principal)
	{
		User user = this.userService.findByUsername(principal.getName());
		model.addAttribute("roleId",user.getRoleId());
		model.addAttribute("firstName",user.getFirstName()+" "+user.getLastName());
		
		model.addAttribute("command",new Technology());
		
		return "save-tech";
	}
	
	@RequestMapping("/save-tech")
	public String saveTech(@ModelAttribute("tech") Technology tech,Model model,Principal principal)
	{
		User user = this.userService.findByUsername(principal.getName());
		model.addAttribute("roleId",user.getRoleId());
		model.addAttribute("firstName",user.getFirstName()+" "+user.getLastName());
		
		boolean status = this.techService.save(tech);
		
		if(!status)
			throw new GlobalException("Technology insertion failed!");
		
		return "redirect:/admin/add-tech";
	}
	
	@RequestMapping("/edit-tech/{techId}")
	public String editTech(@PathVariable("techId")Integer techId,Model model,Principal principal)
	{
		User user = this.userService.findByUsername(principal.getName());
		model.addAttribute("roleId",user.getRoleId());
		model.addAttribute("firstName",user.getFirstName()+" "+user.getLastName());
		
		Technology tech = this.techService.getById(techId);
		
		model.addAttribute("command",tech);
		
		return "edit-tech";
	}
	
	@RequestMapping("/update-tech")
	public String updateTech(@ModelAttribute("tech") Technology tech,Model model,Principal principal)
	{
		User user = this.userService.findByUsername(principal.getName());
		model.addAttribute("roleId",user.getRoleId());
		model.addAttribute("firstName",user.getFirstName()+" "+user.getLastName());
		
		boolean status = this.techService.update(tech);
		
		if(!status)
			throw new GlobalException("Tecgnology updation failed!");
		
		return "redirect:/admin/view-tech";
	}
	
	@RequestMapping("/delete-tech/{techId}")
	public String deleteTech(@PathVariable("techId")Integer techId,Model model,Principal principal)
	{
		User user = this.userService.findByUsername(principal.getName());
		model.addAttribute("roleId",user.getRoleId());
		model.addAttribute("firstName",user.getFirstName()+" "+user.getLastName());
		
		boolean status = this.techService.delete(techId);
		
		if(!status)
			throw new GlobalException("Technology deletion failed!");
		
		return "redirect:/admin/view-tech";
	}
	
	//fee payment
	@RequestMapping("/payment/{trainingId}")
	public String paymentTable(@PathVariable("trainingId") Integer id,Model model,Principal principal)
	{
		User user = this.userService.findByUsername(principal.getName());
		model.addAttribute("roleId",user.getRoleId());
		model.addAttribute("firstName",user.getFirstName()+" "+user.getLastName());
		
		Training training = this.trainingService.getById(id);
		
		TrainingData trainingData = null;
		
		String mentorName = this.userService.getById(training.getMentorId()).getFirstName();
		String userName = this.userService.getById(training.getUserId()).getFirstName();
		String slotTimeFrom = this.slotService.getById(training.getSlotId()).getTimeFrom().toString(); 
		String slotTimeTo = this.slotService.getById(training.getSlotId()).getTimeTo().toString();
		String techName = this.techService.getById(training.getTechId()).getTechnologyName();
		String action = "Running";
		
		trainingData = new TrainingData(training.getId(), mentorName, userName, slotTimeFrom, slotTimeTo, techName,training.getProgress(),training.getStartDate(),training.getEndDate(),training.getTotalFee(),training.getAmountReceived(),training.getInstallmentStatus(),training.getRating(),action);
		
		model.addAttribute("training",trainingData);
		
		return "payment-table";
	}
	
	@RequestMapping("/paypal/{trainingId}")
	public String paypal(@PathVariable("trainingId") Integer id,Model model,Principal principal)
	{
		User user = this.userService.findByUsername(principal.getName());
		model.addAttribute("roleId",user.getRoleId());
		model.addAttribute("firstName",user.getFirstName()+" "+user.getLastName());
		
		Training training = this.trainingService.getById(id);
		model.addAttribute("training",training);
		
		String mentorName = this.userService.getById(training.getMentorId()).getFirstName();
		model.addAttribute("mentorName",mentorName);
		
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String dateTime = format.format(date);
		
		model.addAttribute("dateTime",dateTime);
		
		model.addAttribute("command",new Payment());
		
		return "payment-form";
	}
	
	@RequestMapping(value = "/pay",method = RequestMethod.POST)
	public String payment(@ModelAttribute("payment") Payment payment,Model model,Principal principal)
	{
		User user = this.userService.findByUsername(principal.getName());
		model.addAttribute("roleId",user.getRoleId());
		model.addAttribute("firstName",user.getFirstName()+" "+user.getLastName());
		
		Training training = this.trainingService.getById(payment.getTrainingId());
		model.addAttribute("training",training);
		
		boolean payStatus = this.payService.savePayment(payment);
		
		if(payStatus)
		{
			boolean status = this.trainingService.paymentMethod(payment.getTrainingId(),payment.getPayment(),payment.getInstallmentStatus());
			
			//transaction backtrack code is here
			
			if(!status)
				throw new GlobalException("Payment method failed!");
		}
		else
		{
			throw new GlobalException("Payment failed!");
		}
		
		return "redirect:/admin/home";
	}
	
	@RequestMapping("/running-payment")
	public String runningPayment(Model model,Principal principal)
	{
		User user = this.userService.findByUsername(principal.getName());
		model.addAttribute("roleId",user.getRoleId());
		model.addAttribute("firstName",user.getFirstName()+" "+user.getLastName());
		
		PaymentList payList = this.payService.getAllPayment();
		
		model.addAttribute("payList",payList.getListPayment());
		
		return "running-payment";
	}
	
	@RequestMapping("/complete-payment")
	public String completePayment(Model model,Principal principal)
	{
		User user = this.userService.findByUsername(principal.getName());
		model.addAttribute("roleId",user.getRoleId());
		model.addAttribute("firstName",user.getFirstName()+" "+user.getLastName());
		
		PaymentList payList = this.payService.getCompletePayment();
		
		model.addAttribute("payList",payList.getListPayment());
		
		return "complete-payment";
	}
	
	//user block/unblock section
	@RequestMapping("/user")
	public String user(Model model,Principal principal)
	{
		User user = this.userService.findByUsername(principal.getName());
		model.addAttribute("roleId",user.getRoleId());
		model.addAttribute("firstName",user.getFirstName()+" "+user.getLastName());
		
		UserList userList = this.userService.getAll();
		model.addAttribute("userList",userList.getUserList());
		
		return "user";
	}

	@RequestMapping("/activate/{userId}/{activate}")
	public String activation(@PathVariable("userId") Integer userId,@PathVariable("activate") Integer activate,Model model,Principal principal)
	{
		User user = this.userService.findByUsername(principal.getName());
		model.addAttribute("roleId",user.getRoleId());
		model.addAttribute("firstName",user.getFirstName()+" "+user.getLastName());
		
		boolean userStatus = this.userService.getActiveStatus(userId, activate);
		
		if(!userStatus)
			throw new GlobalException("Activation updation failed!");
		
		return "redirect:/admin/user";
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
