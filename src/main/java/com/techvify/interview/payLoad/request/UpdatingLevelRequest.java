package com.techvify.interview.payLoad.request;


import com.techvify.interview.entity.Level;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

public class UpdatingLevelRequest {
	
	private int id;

	@NotBlank(message = "{Level.createLevel.form.name.NotBlank}")
	@Length(max = 20, message = "{Level.createLevel.form.name.Length}")
	private String name;

	@NotBlank(message = "{Level.createLevel.form.code.NotBlank}")
	@Length(max = 20, message = "{Level.createLevel.form.code.Length}")
	private String code;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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

	public UpdatingLevelRequest() {
	}

	public Level toEntity() {
		return new Level(id,name,code);
	}
	
	
}
