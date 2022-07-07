package com.techvify.interview.entity;


import com.techvify.interview.entity.Question;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Answer")
public class Answer implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "name", length = 250, nullable = false)
	private String name;


	@Column(name = "is_correct", nullable = false)
	@Enumerated(EnumType.STRING)
	private Correct isCorrect;

	@ManyToOne
	@JoinColumn(name = "question_id", nullable = false)
	private Question question;

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


	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public Correct getIsCorrect() {
		return isCorrect;
	}

	public void setIsCorrect(Correct isCorrect) {
		this.isCorrect = isCorrect;
	}


	public enum Correct {
		TRUE, FALSE
	}
}
