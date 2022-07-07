package com.techvify.interview.payLoad.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuestionRequest {

    private int id;

    @NotBlank(message = "The name mustn't be null value")
    private String name;

//   @NotBlank(message = "The Level Id mustn't be null value")
    private int levelId;

//  @NotBlank(message = "The programming Language Id mustn't be null value")
    private int programmingLanguageId;

    private boolean questionType;

//  @NotBlank(message = "The framework Id mustn't be null value")
    private int frameworkId;

}

