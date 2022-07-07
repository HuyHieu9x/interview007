package com.techvify.interview.controller;

import com.techvify.interview.entity.Level;
import com.techvify.interview.payLoad.request.LevelRequestForCreating;
import com.techvify.interview.payLoad.request.UpdatingLevelRequest;
import com.techvify.interview.payLoad.response.LevelResponse;
import com.techvify.interview.service.interfaceservice.ILevelService;
import com.techvify.interview.validation.level.LevelIDExists;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "api/v1/levels")
@Validated
public class LevelController {
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private ILevelService service;

	@GetMapping()
	public ResponseEntity<?> getAllLevels(Pageable pageable,
			@RequestParam(value = "search",required = false)
			String search) {
		Page<Level> entities = service.getAllLevels(pageable,search);
		
		// convert entities --> dtos
		List<LevelResponse> dtos = modelMapper.map(
				entities.getContent(),
				new TypeToken<List<LevelResponse>>(){}.getType());
		
		Page<LevelResponse> dtoPages = new PageImpl<>(dtos, pageable, entities.getTotalElements());
		return new ResponseEntity<>(dtoPages, HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<?> getLevelByID(@PathVariable(name = "id") @LevelIDExists int id) {
		Level entity = service.getLevelByID(id);
		
		// convert entity to dto
		LevelResponse dto = modelMapper.map(entity, LevelResponse.class);
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}
	
	@PostMapping()
	public ResponseEntity<?> createLevel(@RequestBody @Valid LevelRequestForCreating form) {
		 service.createLevel(form);
		return new ResponseEntity<>("Create successfuly", HttpStatus.OK);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<?> updateLevel(@LevelIDExists @PathVariable(name = "id") int id,@Valid @RequestBody UpdatingLevelRequest form) {
		service.updateLevel(id,form);
		return new ResponseEntity<>("Update successfuly", HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> deleteLevel(@PathVariable(name = "id") int id) {
		service.deleteLevel(id);
		return new ResponseEntity<>("Delete successfuly", HttpStatus.OK);
	}
}
