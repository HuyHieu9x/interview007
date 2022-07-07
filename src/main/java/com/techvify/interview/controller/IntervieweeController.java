package com.techvify.interview.controller;

import java.util.List;

import javax.validation.Valid;

import com.techvify.interview.payLoad.request.IntervieweeFilterRequest;
import com.techvify.interview.payLoad.request.IntervieweeRequestForCreating;
import com.techvify.interview.payLoad.request.UpdatingForIntervieweeRequest;
import com.techvify.interview.payLoad.response.IntervieweeResponse;
import com.techvify.interview.service.interfaceservice.IIntervieweeService;
import com.techvify.interview.validation.interviewee.IntervieweeIDExists;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techvify.interview.entity.Interviewee;


@RestController
@RequestMapping(value = "api/v1/interviewees")
@Validated
public class IntervieweeController {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private IIntervieweeService service;

	@GetMapping()
	public ResponseEntity<?> getAllInterviewees(Pageable pageable,
												@RequestParam(value = "search",required = false)
			String search, IntervieweeFilterRequest filterLevelName) {
		
		Page<Interviewee> entities = service.getAllInterviewees(pageable,search,filterLevelName);
		
		// convert entities --> dtos
		List<IntervieweeResponse> dtos = modelMapper.map(
				entities.getContent(),
				new TypeToken<List<IntervieweeResponse>>(){}.getType());
		
		Page<IntervieweeResponse> dtoPages = new PageImpl<>(dtos, pageable, entities.getTotalElements());
		return new ResponseEntity<>(dtoPages, HttpStatus.OK);
		
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<?> getIntervieweeByID(@PathVariable(name = "id")@IntervieweeIDExists int id) {
		Interviewee entity = service.getIntervieweeByID(id);
		
		// convert entity to dto
		IntervieweeResponse dto = modelMapper.map(entity, IntervieweeResponse.class);
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}
	
	@PostMapping()
	public ResponseEntity<?> createInterviewee(@RequestBody @Valid IntervieweeRequestForCreating form) {
		service.createInterviewee(form);
		return new ResponseEntity<>("Create successfully", HttpStatus.OK);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<?>  updateInterviewee(@IntervieweeIDExists @PathVariable(name = "id") int id,@Valid @RequestBody UpdatingForIntervieweeRequest form) {
		service.updateInterviewee(id,form);
		return new ResponseEntity<>("Update successfully", HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> deleteInterviewee(@PathVariable(name = "id") int id) {
		service.deleteInterviewee(id);
		return new ResponseEntity<>("Delete successfully", HttpStatus.OK);
	}
	

}
