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
import org.springframework.web.bind.annotation.RequestParam;

import com.mentorondemand.entity.MentorSkill;
import com.mentorondemand.entity.NotificationData;
import com.mentorondemand.entity.Training;
import com.mentorondemand.entity.TrainingData;
import com.mentorondemand.entity.TrainingList;
import com.mentorondemand.entity.User;
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
	
	//logout
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		session.invalidate();

		return "redirect:/signin";
	}
	
	//home search section
	@RequestMapping("/home")
	public String searchAll(Model model,HttpServletRequest request)
	{
		HttpSession session = request.getSession(false);
		if(session.getAttribute("user") == null)
		{
			return "redirect:/student/logout";
		}
		User user = (User) session.getAttribute("user");
		model.addAttribute("roleId",user.getRoleId());
		//model.addAttribute("userId",user.getId());
		
		model.addAttribute("search",this.search.searchAll().getSearchList());
		
		//notification bar
		TrainingList notifyList = this.trainingService.getActionTraining(user.getId());
		
		List<NotificationData> notificationDataList = new ArrayList<NotificationData>();
		NotificationData notificationData = null;
		
		for(Training training : notifyList.getTrainingList())
		{
			String userName = this.userService.getById(training.getUserId()).getFirstName();
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
	public String searchById(Model model,@RequestParam("techId") String techId,HttpServletRequest request)
	{
		HttpSession session = request.getSession(false);
		if(session.getAttribute("user")==null)
		{
			return "redirect:/student/logout";
		}
		User user = (User) session.getAttribute("user");
		model.addAttribute("roleId",user.getRoleId());
		
		model.addAttribute("search",this.search.searchById(Integer.parseInt(techId)).getSearchList());

		return "index";
    }
	
	//send proposal
	@RequestMapping("/send-proposal/{mentorId}/{userId}/{slotId}/{techId}")
	public String sendProposal(@PathVariable("mentorId") Integer mentorId,@PathVariable("userId") Integer userId,@PathVariable("slotId") Integer slotId,@PathVariable("techId") Integer techId,Model model,HttpServletRequest request)
	{
		HttpSession session = request.getSession(false);
		if(session.getAttribute("user")==null)
		{
			return "redirect:/student/logout";
		}
		User user = (User) session.getAttribute("user");
		model.addAttribute("roleId",user.getRoleId());
		
		Training training = new Training(null,mentorId,userId,slotId,techId,0,null,null,0.0,0.0,0,5,0,1);
		
		boolean status = this.trainingService.save(training);
		
		if(!status)
			return "redirect:/student/training";
		
		return "redirect:/student/training";
	}
	
	//edit profile
	@RequestMapping("/edit-profile")
	public String editProfile(Model model,HttpServletRequest request)
	{
		HttpSession session = request.getSession(false);
		if(session.getAttribute("user") == null)
		{
			return "redirect:/student/logout";
		}
		User user = (User) session.getAttribute("user");
		model.addAttribute("roleId",user.getRoleId());
		
		model.addAttribute("command",user);
		
		return "user-edit-profile";
	}
	
	@RequestMapping(value="/update-profile",method = RequestMethod.POST)
	public String updateProfile(@ModelAttribute("user") User user,HttpServletRequest request,Model model)
	{
		HttpSession session = request.getSession(false);
		if(session.getAttribute("user") == null)
		{
			return "redirect:/student/logout";
		}
		User user1 = (User) session.getAttribute("user");
		model.addAttribute("roleId",user1.getRoleId());
		
		this.userService.editUser(user);
		
		User user2 = this.userService.getById(user.getId());
		request.getSession().removeAttribute("user");
		request.getSession().setAttribute("user",user2);
		
		return "redirect:/student/edit-profile";
	}
	
	//training section
	@RequestMapping("/training")
	public String training(Model model,HttpServletRequest request)
	{
		HttpSession session = request.getSession(false);
		if(session.getAttribute("user")==null)
		{
			return "redirect:/student/logout";
		}
		User user = (User) session.getAttribute("user");
		model.addAttribute("roleId",user.getRoleId());
		
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
	public String payment(@PathVariable("trainingId") Integer trainingId,Model model,HttpServletRequest request)
	{
		HttpSession session = request.getSession(false);
		if(session.getAttribute("user")==null)
		{
			return "redirect:/student/logout";
		}
		User user = (User) session.getAttribute("user");
		model.addAttribute("roleId",user.getRoleId());
		
		Training training = this.trainingService.getById(trainingId);
		MentorSkill skill = this.skillService.getSkillByMentorIdAndTechId(training.getMentorId(),training.getTechId());
		String mantor = this.userService.getById(training.getMentorId()).getFirstName();
		
		model.addAttribute("mantor",mantor);
		model.addAttribute("trainingFee",skill.getFee());
		model.addAttribute("training",training);
		
		return "payment-fee";
	}
	
	@RequestMapping(value = "/paypal",method = RequestMethod.POST)
	public String paypal(@ModelAttribute("training") Training training,Model model,HttpServletRequest request)
	{
		HttpSession session = request.getSession(false);
		if(session.getAttribute("user")==null)
		{
			return "redirect:/student/logout";
		}
		User user = (User) session.getAttribute("user");
		model.addAttribute("roleId",user.getRoleId());
		
		Training training1 = this.trainingService.getById(training.getId());
		training1.setTotalFee(training.getTotalFee());
		training1.setRequest(3);
		
		boolean status = this.trainingService.update(training1);
		
		if(!status)
			return "redirect:/student/training";
		
		return "redirect:/student/training";
	}
	
	//rating section
	@RequestMapping(value = "/rating/{editId}/{rating}",method = RequestMethod.GET)
	public String rating(@PathVariable("editId") Integer id,@PathVariable("rating") Integer rating)
	{
		Training training = this.trainingService.getById(id);
		training.setRating(rating);
		
		boolean status = this.trainingService.update(training);
		
		if(!status)
			return "redirect:/student/training";
		
		return "redirect:/student/training";
	}
}
