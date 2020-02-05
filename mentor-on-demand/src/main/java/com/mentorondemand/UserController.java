/*package com.mentorondemand.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mentorondemand.entity.SearchList;
import com.mentorondemand.entity.StatusResponse;
import com.mentorondemand.entity.TrainingList;
import com.mentorondemand.exception.DataNotFoundException;
import com.mentorondemand.exception.ExceptionErrorResponse;
import com.mentorondemand.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService service;
	
	//done - searching by tech id
	@GetMapping("/search/{techId}")
	public ResponseEntity<SearchList> searchById(@PathVariable("techId") Integer techId)
	{
		SearchList list = this.service.searchById(techId);
		ResponseEntity<SearchList> responseEntity = new ResponseEntity<SearchList>(list,HttpStatus.OK);
		
		if(list == null)
			throw new DataNotFoundException("Search list is empty!!!");
		
		return responseEntity;
	}
	
	//done - sending a proposal to mentor by user in specific slot timing and technology
	@GetMapping("/sendProposal/{mentorId}/{userId}/{slotId}/{techId}")
	public ResponseEntity<StatusResponse> sendProposal(@PathVariable("mentorId") Integer mentorId,@PathVariable("userId") Integer userId,@PathVariable("slotId") Integer slotId,@PathVariable("techId") Integer techId)
	{
		StatusResponse status = this.service.sendProposal(mentorId, userId, slotId, techId);
		ResponseEntity<StatusResponse> responseEntity = new ResponseEntity<StatusResponse>(status,HttpStatus.OK);
		
		if(status == null)
			throw new DataNotFoundException("Sending proposal is failed!!!");
		
		return responseEntity;
	}
	
	//done - give rating value in training table and get the average rating of training and update average mentor skill avg_rating field. 
	@GetMapping("/rating/{trainingId}")
	public ResponseEntity<StatusResponse> giveRating(@PathVariable("trainingId") Integer trainingId)
	{
		//take rating value within 1 to 5 in rating variable 
		Integer rating = 4;
		StatusResponse status = this.service.giveRating(trainingId,rating);
		ResponseEntity<StatusResponse> responseEntity = new ResponseEntity<StatusResponse>(status,HttpStatus.OK);
		
		if(status == null)
			throw new DataNotFoundException("Giving rating is failed!!!");
		
		return responseEntity;
	}
	
	//done - get all training for specific user that are logged in 
	@GetMapping("/getAllTrainingDetail")
	public MentorDetailList getAllTrainingDetail()
	{
		//sent user session id in method
		//take userSessionId after login from the session
		Integer userSessionId = 6;
		return this.service.getAllTrainingById(userSessionId);
	}
	@GetMapping("/getAllTrainingDetail")
	public ResponseEntity<TrainingList> getAllTrainingDetail()
	{
		//sent user session id in method
		//take userSessionId after login from the session
		Integer userSessionId = 6;
		TrainingList list = this.service.getAllTrainingById(userSessionId);
		ResponseEntity<TrainingList> responseEntity = new ResponseEntity<TrainingList>(list,HttpStatus.OK);
		
		if(list == null)
			throw new DataNotFoundException("Training list is empty!!!");
		
		return responseEntity;
	}
		
	//done - get all current training for specific user that are after logged in
	@GetMapping("/getCurrentTrainingDetail")
	public ResponseEntity<TrainingList> getCurrentTrainingDetail()
	{
		Integer userSessionId = 6;
		TrainingList list = this.service.getCurrentTrainingDetail(userSessionId);
		ResponseEntity<TrainingList> responseEntity = new ResponseEntity<TrainingList>(list,HttpStatus.OK);
		
		if(list == null)
			throw new DataNotFoundException("Training running list is empty!!!");
		
		return responseEntity;
	}
	
	//done - get all complete training for specific user that are after logged in
	@GetMapping("/getCompleteTrainingDetail")
	public ResponseEntity<TrainingList> getCompleteTrainingDetail()
	{
		Integer userSessionId = 6;
		TrainingList list = this.service.getCompleteTrainingDetail(userSessionId);
		ResponseEntity<TrainingList> responseEntity = new ResponseEntity<TrainingList>(list,HttpStatus.OK);
		
		if(list == null)
			throw new DataNotFoundException("Training complete list is empty!!!");
		
		return responseEntity;
	}

	 Exception handler 
	
	@ExceptionHandler
	public ResponseEntity<ExceptionErrorResponse> handler(DataNotFoundException ex)
	{
		ExceptionErrorResponse error = new ExceptionErrorResponse(ex.getMessage(),HttpStatus.NOT_FOUND.value(),System.currentTimeMillis());
		
		ResponseEntity<ExceptionErrorResponse> responseEntity = new ResponseEntity<ExceptionErrorResponse>(error,HttpStatus.NOT_FOUND);
		
		return responseEntity;
	}
}
*/