package com.techvify.interview.payLoad.request;

import org.hibernate.annotations.CreationTimestamp;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;


public class UpdatingForQuestionRequest {
    private int id;

    @NotBlank(message = "The name mustn't be null value")
    private String name;

    @NotBlank(message = "The Id mustn't be null value")
    private int levelId;

    @NotBlank(message = "The programming Language Id mustn't be null value")
    private int programmingLanguageId;
    @NotBlank(message = "The framework Id mustn't be null value")
    private int frameworkId;
    private boolean type;
    
    @CreationTimestamp
    private LocalDateTime updatedAt;

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

    public int getLevelId() {
        return levelId;
    }

    public void setLevelId(int levelId) {
        this.levelId = levelId;
    }

    public int getProgrammingLanguageId() {
        return programmingLanguageId;
    }

    public void setProgrammingLanguageId(int programmingLanguageId) {
        this.programmingLanguageId = programmingLanguageId;
    }

    public boolean isType() {
        return type;
    }

    public void setType(boolean type) {
        this.type = type;
    }

    public int getFrameworkId() {
        return frameworkId;
    }

    public void setFrameworkId(int frameworkId) {
        this.frameworkId = frameworkId;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public UpdatingForQuestionRequest() {
    }

    public UpdatingForQuestionRequest(int id, String name, int levelId, int programmingLanguageId, boolean type,
                                      int frameworkId, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.levelId = levelId;
        this.programmingLanguageId = programmingLanguageId;
        this.type = type;
        this.frameworkId = frameworkId;
        this.updatedAt = updatedAt;
    }


}
