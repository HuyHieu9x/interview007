package com.techvify.interview.payLoad.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AnswerResponse {
    private String name;
    private String isCorrect;
    private String questionName;
}
