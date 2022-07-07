package com.techvify.interview.entity;

import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "title")
@SQLDelete(sql = "UPDATE title SET deleted_at = true WHERE id = ?")

@FilterDef(name = "deletedTitleFilter", parameters = @ParamDef(name = "isDeleted", type = "boolean"))
@Filter(name = "deletedTitleFilter", condition = "deleted_at = :isDeleted")

public class Title {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "deleted_at")
    private boolean deletedAt = Boolean.FALSE;

    @OneToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST})
    @JoinColumn(name = "title_id")
    private List<Interviewer> interviewerList;

    public Title() {
    }

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

    public boolean isDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(boolean deletedAt) {
        this.deletedAt = deletedAt;
    }

    public List<Interviewer> getInterviewerList() {
        return interviewerList;
    }

    public void setInterviewerList(List<Interviewer> interviewerList) {
        this.interviewerList = interviewerList;
    }

}
