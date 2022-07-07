package com.techvify.interview.payLoad.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import com.techvify.interview.validation.interviewee.IntervieweeEmailNotExists;
import org.hibernate.validator.constraints.Length;

public class IntervieweeRequestForCreating {
	
	@NotBlank(message = "{Interviewee.createInterviewee.form.name.NotBlank}")
	@Length(max = 50, message = "{Interviewee.createInterviewee.form.name.Length}")
	private String name;
	
	@NotBlank(message = "{Interviewee.createInterviewee.form.email.NotBlank}")
	@Email(message = "{Interviewee.createInterviewee.form.email.Email}")
	@IntervieweeEmailNotExists
	private String email;
	
	@Positive(message = "{Interviewee.createInterviewee.form.levelId.Positive}")
	private int levelId;

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

	public IntervieweeRequestForCreating() {
	}
	
	
}
