package com.techvify.interview.entity;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "Level")
public class Level implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Column(name = "id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "name",length = 20, nullable = false)
	private String name;
	
	@Column(name = "code",length = 20, nullable = false,unique = true)
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

	public Level() {
	}

	public Level(int id, String name, String code) {
		this.id = id;
		this.name = name;
		this.code = code;
	}

}