package com.mentorondemand.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.mentorondemand.constraint.UserValidator;
import com.mentorondemand.entity.MentorSkill;
import com.mentorondemand.entity.SkillList;
import com.mentorondemand.entity.Technology;
import com.mentorondemand.entity.User;
import com.mentorondemand.entity.UserDto;
import com.mentorondemand.exception.GlobalException;
import com.mentorondemand.exception.LoginException;
import com.mentorondemand.facade.MentorSkillService;
import com.mentorondemand.facade.TechnologyService;
import com.mentorondemand.service.SearchService;
import com.mentorondemand.service.UserServiceImpl;

@Controller
public class HomeController {

	@Autowired
	private SearchService search;
	@Autowired
	private MentorSkillService mentorSkill;
	@Autowired
	private TechnologyService techService;
	@Autowired
	private UserServiceImpl user;
	@Autowired
	private UserValidator userValidator;

	//Search section
	@RequestMapping("/")
	public String searchAll(Model model)
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		if(!(auth instanceof AnonymousAuthenticationToken))
		{
			Set<String> roles = auth.getAuthorities().stream()
				     .map(r -> r.getAuthority()).collect(Collectors.toSet());
			
			if(roles.contains("ROLE_ADMIN"))
				return "redirect:/admin/home";
			else
			if(roles.contains("ROLE_USER"))
				return "redirect:/student/home";
			else
				return "redirect:/mentor/home";
		}
		
		List<Integer> techSkillList = this.mentorSkill.getAllUniqueSkill();
		
		List<Technology> technology = new ArrayList<>();
		
		for(Integer techId : techSkillList)
		{
			technology.add(this.techService.getById(techId));
		}
		
		model.addAttribute("techList",technology);
		model.addAttribute("search",search.searchAll().getSearchList());
		
		return "index";
    }

	@RequestMapping(value="/search",method=RequestMethod.GET)
	public String searchById(Model model,@RequestParam("techId") String techId)
	{
		List<Integer> techSkillList = this.mentorSkill.getAllUniqueSkill();
		
		List<Technology> technology = new ArrayList<>();
		
		for(Integer tId : techSkillList)
		{
			technology.add(this.techService.getById(tId));
		}
		
		model.addAttribute("techList",technology);
		model.addAttribute("search",search.searchById(Integer.parseInt(techId)).getSearchList());
		
		return "index";
    }

	//Register section
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String registration(Model model)
    {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		if(!(auth instanceof AnonymousAuthenticationToken))
		{
			Set<String> roles = auth.getAuthorities().stream()
				     .map(r -> r.getAuthority()).collect(Collectors.toSet());
			
			if(roles.contains("ROLE_ADMIN"))
				return "redirect:/admin/home";
			else
			if(roles.contains("ROLE_USER"))
				return "redirect:/student/home";
			else
				return "redirect:/mentor/home";
		}
		
        model.addAttribute("userForm", new UserDto());

        return "register";
    }

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") UserDto userForm, BindingResult bindingResult, Model model)
    {
    	this.userValidator.validate(userForm,bindingResult);
    	
        if(bindingResult.hasErrors())
        {
        	return "register";
        }
        
        User user = new User();
        
        user.setUserName(userForm.getUserName());
        user.setPassword(userForm.getPassword());
        user.setConfirmPassword(userForm.getConfirmPassword());
        user.setFirstName(userForm.getFirstName());
        user.setLastName(userForm.getLastName());
        user.setContact(userForm.getContact());
        for(Integer role : userForm.getRoles())
        {
        	user.setRoleId(role);
        }
        user.setLinkedInUrl(userForm.getLinkedInUrl());
        user.setYearOfExp(userForm.getYearOfExp());
        user.setIsActive(userForm.getIsActive());
        
        this.user.save(user,userForm.getRoles());
        
        return "redirect:/signin";
    }

	// Login section
	@RequestMapping(value = {"/signin"}, method = RequestMethod.GET)
	public String signin(@RequestParam(value = "error",required = false) String error,@RequestParam(value = "logout",	required = false) String logout,Model model)
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		if(!(auth instanceof AnonymousAuthenticationToken))
		{
			Set<String> roles = auth.getAuthorities().stream()
				     .map(r -> r.getAuthority()).collect(Collectors.toSet());
			
			if(roles.contains("ROLE_ADMIN"))
				return "redirect:/admin/home";
			else
			if(roles.contains("ROLE_USER"))
				return "redirect:/student/home";
			else
				return "redirect:/mentor/home";
		}
		
		if(error != null)
		{
			model.addAttribute("error", "Invalid credentials provided.");
		}

		if(logout != null)
		{
			model.addAttribute("message", "Logged out successfully.");
		}
		
		return "login";
	}

	//exception handling
	@ExceptionHandler
	public String handleLoginException(LoginException ex,Model model)
	{
		model.addAttribute("exception",ex);
		
		return "error";
	}

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
}
