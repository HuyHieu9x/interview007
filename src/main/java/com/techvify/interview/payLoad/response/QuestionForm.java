package com.techvify.interview.payLoad.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuestionForm {

    private int id;

    private String name;

    private int levelId;

    private int programmingLanguageId;

    private boolean questionType;

    private int frameworkId;

}

