package com.techvify.interview.validation.level;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.techvify.interview.service.interfaceservice.ILevelService;

public class LevelIDExistsValidator implements ConstraintValidator<LevelIDExists, Integer>{
	
	@Autowired
	private ILevelService service;
	
	@SuppressWarnings("deprecation")
	@Override
	public boolean isValid(Integer id, ConstraintValidatorContext context) {
		
		if(StringUtils.isEmpty(id)) {
			return true;
		}
		
		return service.isLevelExistsByID(id);
	}
}
