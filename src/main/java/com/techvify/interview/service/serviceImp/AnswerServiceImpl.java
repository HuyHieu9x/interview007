package com.techvify.interview.service.serviceImp;

import com.techvify.interview.entity.Answer;
import com.techvify.interview.payLoad.request.AnswerRequest;
import com.techvify.interview.payLoad.request.Filter;
import com.techvify.interview.repository.IAnswerRepository;
import com.techvify.interview.service.interfaceservice.IAnswerService;
import com.techvify.interview.specification.Specifications;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerServiceImpl implements IAnswerService {

    @Autowired
    ModelMapper modelMapper;
    @Autowired
    IAnswerRepository answerRepository;

    @Override
    public Page<Answer> getAnswerByKeyword(Pageable pageable, String search, Filter answerFilter) {
        Specification<Answer> where = Specifications.buildWhere(search, answerFilter);
        return answerRepository.findAll(where, pageable);
    }

    @Override
    public void deleteById(int id) {
        Answer answer = answerRepository.findById(id).get();
        answerRepository.deleteById(answer.getId());
    }

    @Override
    public List<Answer> getAnswerList() {
        return answerRepository.findAll();
    }

    @Override
    public <E> void create(E object) {
        TypeMap<AnswerRequest, Answer> typeMap = modelMapper.getTypeMap(AnswerRequest.class, Answer.class);
        if (typeMap == null){
            modelMapper.addMappings(new PropertyMap<AnswerRequest, Answer>() {

                @Override
                protected void configure() {
                    skip(destination.getId());
                }
            });
        }
        Answer answer = modelMapper.map(object, Answer.class);
        answerRepository.save(answer);
    }

    @Override
    public void update(int id,AnswerRequest answerRequest) {

        Answer oldAnswer = answerRepository.findById(id).get();
        TypeMap<AnswerRequest, Answer> typeMap = modelMapper.getTypeMap(AnswerRequest.class, Answer.class);
        Answer newAnswer = modelMapper.map(answerRequest, Answer.class);
        oldAnswer.setName(newAnswer.getName());
        oldAnswer.setIsCorrect(newAnswer.getIsCorrect());
        answerRepository.save(oldAnswer);
    }

    @Override
    public Answer getQuestionByID(int id) {
        return answerRepository.findById(id).get();
    }
}
