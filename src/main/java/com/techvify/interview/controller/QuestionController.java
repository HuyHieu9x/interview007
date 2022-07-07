package com.techvify.interview.controller;

import com.techvify.interview.entity.Question;
import com.techvify.interview.payLoad.request.Filter;
import com.techvify.interview.payLoad.request.QuestionRequest;
import com.techvify.interview.payLoad.request.UpdatingForQuestionRequest;
import com.techvify.interview.payLoad.response.QuestionResponse;
import com.techvify.interview.service.interfaceservice.IQuestionService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/question")
@Validated
public class QuestionController {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private IQuestionService service;

    @GetMapping("/all")
    public ResponseEntity<?> getAllInterviewees(Pageable pageable,
                                                @RequestParam(value = "search",required = false)
                                                   String search, Filter questionFilter) {

        Page<Question> entities = service.getFindAll(pageable,search,questionFilter);

        // convert entities --> dtos
        List<QuestionResponse> dtos = modelMapper.map(
                entities.getContent(),
                new TypeToken<List<QuestionResponse>>(){}.getType());

        Page<QuestionResponse> dtoPages = new PageImpl<>(dtos, pageable, entities.getTotalElements());
        return new ResponseEntity<>(dtoPages, HttpStatus.OK);

    }


    @PostMapping()
    public ResponseEntity<?> createQuestion(@RequestBody @Valid QuestionRequest form) {
        service.create(form);
        return new ResponseEntity<>("Create successfully", HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id")  int id) {
        service.deleteById(id);
        return new ResponseEntity<>("Delete successfully", HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getQuestionByID(@PathVariable(name = "id") int id) {
        Question entity = service.getQuestionByID(id);

        // convert entity to dto
        QuestionResponse request = modelMapper.map(entity, QuestionResponse.class);
        return new ResponseEntity<>(request, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateQuestion(@PathVariable(name = "id")  int id, @RequestBody UpdatingForQuestionRequest form) {
        service.updateQuestion(id,form);
        return new ResponseEntity<>("Update successfully", HttpStatus.OK);
    }

}
