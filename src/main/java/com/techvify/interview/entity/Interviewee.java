package com.techvify.interview.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Interviewee")
public class Interviewee implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Column(name = "id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "name",length = 50,nullable = false)
	private String name;
	
	@Column(name = "email",length = 150,nullable = false)
	private String email;
	
	@ManyToOne
	@JoinColumn(name = "level_id",nullable = false)
	private Level level;

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

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

	public Interviewee() {
	}
	
	
	
}
