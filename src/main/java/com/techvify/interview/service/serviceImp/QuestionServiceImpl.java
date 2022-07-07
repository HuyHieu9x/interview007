package com.techvify.interview.service.serviceImp;

import com.techvify.interview.entity.Framework;
import com.techvify.interview.entity.Level;
import com.techvify.interview.entity.ProgrammingLanguage;
import com.techvify.interview.entity.Question;
import com.techvify.interview.payLoad.request.Filter;
import com.techvify.interview.payLoad.request.QuestionRequest;
import com.techvify.interview.payLoad.request.UpdatingForQuestionRequest;
import com.techvify.interview.repository.*;
import com.techvify.interview.service.interfaceservice.IQuestionService;
import com.techvify.interview.specification.Specifications;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class QuestionServiceImpl implements IQuestionService {
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    IQuestionRepository repository;

    @Autowired
    ILevelRepository levelRepository;

    @Autowired
    private IProgrammingLanguageRepository programmingLanguageRepository;

    @Autowired
    private IFrameworkRepository frameworkRepository;
    @Override
    public Page<Question> getFindAll(Pageable pageable, String search, Filter questionFilter) {
        Specification<Question> where = Specifications.buildWhere(search, questionFilter);
        return repository.findAll(where, pageable);
    }

    @Override
    public void deleteById(int id) {
        Question question = repository.findById(id).get();
        repository.deleteById(question.getId());

    }

    @Override
    public List<Question> findAll() {
        return repository.findAll();
    }

    @Override
    public void create(QuestionRequest form) {
        // omit id field
        TypeMap<QuestionRequest,Question> typeMap = modelMapper.getTypeMap(QuestionRequest.class, Question.class);
        if(typeMap == null) { // if not already added
            // skip field
            modelMapper.addMappings(new PropertyMap<QuestionRequest, Question>() {

                @Override
                protected void configure() {
                    skip(destination.getId());

                }
            });
        }

        // convert form to entity
        Question question = modelMapper.map(form, Question.class);
        repository.save(question);
    }

    @Override
    public void updateQuestion(int id, UpdatingForQuestionRequest form) {


        Level levelId = levelRepository.findById(form.getLevelId()).get();
        ProgrammingLanguage programmingLanguageId = programmingLanguageRepository.findById(form.getProgrammingLanguageId()).get();
        Framework frameworkId = frameworkRepository.findById(form.getFrameworkId()).get();

        Question oldQuestion = repository.findById(id).get();
        Question newQuestion = modelMapper.map(form, Question.class);

        oldQuestion.setName(newQuestion.getName());
        oldQuestion.setLevel(levelId);
        oldQuestion.setProgrammingLanguage(programmingLanguageId);
        oldQuestion.setType(newQuestion.isType());
        oldQuestion.setFramework(frameworkId);
        oldQuestion.setUpdatedAt(LocalDateTime.now());

        repository.save(oldQuestion);
    }

    @Override
    public Question getQuestionByID(int id) {
        return repository.findById(id).get();
    }

}
