package com.mentorondemand.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalHandler {

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
	public String handleException(Exception ex,Model model)
	{
		model.addAttribute("exception",ex);
		
		return "error";
	}
}
