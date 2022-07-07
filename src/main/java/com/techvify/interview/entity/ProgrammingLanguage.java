package com.techvify.interview.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "programming_language")
public class ProgrammingLanguage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "programming_language_framework",
            joinColumns = @JoinColumn(name = "programming_language_id"),
            inverseJoinColumns = @JoinColumn(name = "framework_id"))
    private List<Framework> frameworkList;

    public ProgrammingLanguage() {
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

    public List<Framework> getFrameworkList() {
        return frameworkList;
    }

    public void setFrameworkList(List<Framework> frameworkList) {
        this.frameworkList = frameworkList;
    }
}
