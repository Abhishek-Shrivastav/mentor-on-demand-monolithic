package com.mentorondemand.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.mentorondemand.entity.Login;
import com.mentorondemand.entity.User;
import com.mentorondemand.exception.GlobalException;
import com.mentorondemand.exception.LoginException;
import com.mentorondemand.service.SearchService;
import com.mentorondemand.service.UserServiceImpl;

@Controller
public class HomeController {

	@Autowired
	private SearchService search;
	@Autowired
	private UserServiceImpl user;
	
	//Search section
	@RequestMapping("/")
	public String searchAll(Model model,HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		if(session.getAttribute("user")!=null)
		{
			return "redirect:/mentor/home";
		}
		
		model.addAttribute("search",search.searchAll().getSearchList());
		
		return "index";
    }
	
	@RequestMapping(value="/search",method=RequestMethod.GET)
	public String searchById(Model model,@RequestParam("techId") String techId)
	{
		model.addAttribute("search",search.searchById(Integer.parseInt(techId)).getSearchList());
				
		return "index";
    }
	
	//Register section
	@RequestMapping("/signup")
	public String signup(Model model,HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		if(session.getAttribute("user")!=null)
		{
			return "redirect:/mentor/home";
		}
		
		model.addAttribute("user",new User());
		
		return "register";
    }
	
	@RequestMapping(value="/register",method = RequestMethod.POST)
	public String register(@ModelAttribute("user") User user)
	{
		boolean status = this.user.save(user);
		
		if(!status)
			throw new GlobalException("Registration failed!");
			//return "redirect:/";
		
		return "redirect:/signup";
    }
	
	// Login section
	@RequestMapping("/signin")
	public String signin(Model model,HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		
		if(user!=null)
		{
			if(user.getRoleId() == 1)
				return "redirect:/admin/home";
			if(user.getRoleId() == 2)
				return "redirect:/student/home";
			if(user.getRoleId() == 3)
				return "redirect:/mentor/home";
		}
		
		model.addAttribute("login",new Login());
		
		return "login";
	}
	
	@RequestMapping(value="/login",method = RequestMethod.POST)
	public String login(@ModelAttribute("login") Login login,HttpServletRequest request)
	{
		User user = null;
		
		try
		{
			user = this.user.getUserLoginDetail(login.getUsername(),login.getPassword());
		}
		catch(Exception e)
		{
			user = null;
		}
		
		if(user == null)
			throw new LoginException("User or password is invalid!");
		
		HttpSession session = request.getSession();
		session.setAttribute("user",user);
		
		if(user.getRoleId() == 1)	
			return "redirect:/admin/home";
		if(user.getRoleId() == 2)
			return "redirect:/student/home";
		if(user.getRoleId() == 3)
			return "redirect:/mentor/home";
		
		return "error";
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
