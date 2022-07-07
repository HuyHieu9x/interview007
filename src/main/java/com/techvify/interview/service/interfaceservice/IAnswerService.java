package com.techvify.interview.service.interfaceservice;

import com.techvify.interview.entity.Answer;
import com.techvify.interview.payLoad.request.AnswerRequest;
import com.techvify.interview.payLoad.request.Filter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface IAnswerService {
    Page<Answer> getAnswerByKeyword(Pageable pageable, String keyword, Filter answerFilter);

    void deleteById(int id);

    List<Answer> getAnswerList();

    <E> void  create(E object);

    void update(int id, AnswerRequest answerRequest);

    Answer getQuestionByID(int id);
}
