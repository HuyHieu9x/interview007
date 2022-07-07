package com.techvify.interview.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "interview_session")
public class InterviewSession {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;

    @ManyToOne
    @JoinColumn(name = "interviewee_id")
    private Interviewee interviewee;

    @ManyToMany
    @JoinTable(
            name = "interview_session_interviewer",
            joinColumns = @JoinColumn(name = "interview_session_id"),
            inverseJoinColumns = @JoinColumn(name = "interviewer_id"))
    private Set<Interviewer> interviewerSet;

    @ManyToMany
    @JoinTable(
            name = "interview_session_question",
            joinColumns = @JoinColumn(name = "interview_session_id"),
            inverseJoinColumns = @JoinColumn(name = "question_id"))
    private Set<Question> questionSet;

    public InterviewSession() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Interviewee getInterviewee() {
        return interviewee;
    }

    public void setInterviewee(Interviewee interviewee) {
        this.interviewee = interviewee;
    }

    public Set<Interviewer> getInterviewerSet() {
        return interviewerSet;
    }

    public void setInterviewerSet(Set<Interviewer> interviewerSet) {
        this.interviewerSet = interviewerSet;
    }

    public Set<Question> getQuestionSet() {
        return questionSet;
    }

    public void setQuestionSet(Set<Question> questionSet) {
        this.questionSet = questionSet;
    }
}
