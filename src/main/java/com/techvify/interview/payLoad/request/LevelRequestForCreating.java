package com.techvify.interview.payLoad.request;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

public class LevelRequestForCreating {
	
	@NotBlank(message = "{Level.createLevel.form.name.NotBlank}")
	@Length(max = 20, message = "{Level.createLevel.form.name.Length}")
	private String name;
	
	@NotBlank(message = "{Level.createLevel.form.code.NotBlank}")
	@Length(max = 20, message = "{Level.createLevel.form.code.Length}")
	private String code;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public LevelRequestForCreating() {
	}
	
	
}
