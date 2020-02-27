package com.mentorondemand.constraint;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.mentorondemand.entity.User;
import com.mentorondemand.entity.UserDto;
import com.mentorondemand.facade.UserService;

@Component
public class UserValidator implements Validator
{
	@Autowired
	 private UserService userService;
	
	@Override
	public boolean supports(Class<?> clazz) {
		
		return User.class.equals(clazz);
	}

	@Override
	public void validate(Object o, Errors errors) {

		UserDto user = (UserDto) o;
		
		Pattern pattern1 = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
	            Pattern.CASE_INSENSITIVE);
		Pattern pattern2 = Pattern.compile("[0-9]*",
	            Pattern.CASE_INSENSITIVE);
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", "NotEmpty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword", "NotEmpty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "NotEmpty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "NotEmpty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "contact", "NotEmpty");
		
		//userName
		if (!(pattern1.matcher(user.getUserName()).matches())) {
		    errors.rejectValue("userName", "Size.user.userName");
		}
		if (userService.findByUsername(user.getUserName()) != null) {
		    errors.rejectValue("userName", "Duplicate.user.userName");
		}
		//passwords
		if (user.getPassword().length() < 4 || user.getPassword().length() > 32) {
		    errors.rejectValue("password", "Size.user.password");
		}
		if (!user.getConfirmPassword().equals(user.getPassword())) {
		    errors.rejectValue("confirmPassword", "Diff.user.confirmPassword");
		}
		//contact
		if (!(pattern2.matcher(user.getContact()).matches())) {
		    errors.rejectValue("contact", "Size.user.contact");
		}
		if (userService.findByContact(user.getContact()) != null) {
		    errors.rejectValue("contact", "Duplicate.user.contact");
		}
	}
}
