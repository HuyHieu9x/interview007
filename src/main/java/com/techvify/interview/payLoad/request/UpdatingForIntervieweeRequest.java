package com.techvify.interview.payLoad.request;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

public class UpdatingForIntervieweeRequest {
	
	private int id;

	@NotBlank(message = "{Interviewee.createInterviewee.form.name.NotBlank}")
	@Length(max = 50, message = "{Interviewee.createInterviewee.form.name.Length}")
	private String name;

	@NotBlank(message = "{Interviewee.createInterviewee.form.email.NotBlank}")
	@Email(message = "{Interviewee.createInterviewee.form.email.Email}")
	private String email;

	@Positive(message = "{Interviewee.createInterviewee.form.levelId.Positive}")
	private int levelId;

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getLevelId() {
		return levelId;
	}

	public void setLevelId(int levelId) {
		this.levelId = levelId;
	}

	public UpdatingForIntervieweeRequest() {
	}

	
	
	
}
