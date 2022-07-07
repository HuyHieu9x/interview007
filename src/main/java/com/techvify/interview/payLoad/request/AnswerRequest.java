package com.techvify.interview.payLoad.request;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AnswerRequest {
    private String name;
    private String isCorrect;
    private int questionId;
}
