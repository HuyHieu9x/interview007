package com.techvify.interview.service.interfaceservice;

import com.techvify.interview.entity.Question;
import com.techvify.interview.payLoad.request.Filter;
import com.techvify.interview.payLoad.request.QuestionRequest;
import com.techvify.interview.payLoad.request.UpdatingForQuestionRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IQuestionService {
    Page<Question> getFindAll(Pageable pageable, String search, Filter questionFilter);

    void deleteById(int id);

    List<Question> findAll();

    void create(QuestionRequest form);

    void updateQuestion(int id, UpdatingForQuestionRequest form);

    Question getQuestionByID(int id);
}
