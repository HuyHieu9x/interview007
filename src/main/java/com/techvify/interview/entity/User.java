package com.techvify.interview.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Formula;

@Entity
@Table(name = "`User`")
public class User implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id",unique = true,nullable = false)
	private int id;
	
	@Column(name = "`username`",nullable = false,length = 50,unique = true)
	private String userName;
	
	@Column(name = "`email`",nullable = false,length = 50,unique = true)
	private String email;
	
	@Column(name = "`password`",nullable = false,length = 800)
	private String password;
	
	@Column(name = "`firstName`",nullable = false,length = 50)
	private String firstName;
	
	@Column(name = "`lastName`",nullable = false,length = 50)
	private String lastName;
	
	@Formula("concat(firstName,' ',lastName)")
	private String fullName;
	
	@Column(name = "role",nullable = false,length = 50)
	private String role;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public User() {
	}

	public User(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}
	
	
	
	
}
