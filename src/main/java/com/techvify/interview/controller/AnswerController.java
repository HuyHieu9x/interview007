package com.techvify.interview.controller;

import com.techvify.interview.entity.Answer;
import com.techvify.interview.payLoad.request.AnswerRequest;
import com.techvify.interview.payLoad.request.Filter;
import com.techvify.interview.payLoad.response.AnswerResponse;
import com.techvify.interview.service.serviceImp.AnswerServiceImpl;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/answer")
public class AnswerController {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private AnswerServiceImpl service;

    @GetMapping("/list")
    public Page<AnswerResponse> getAnswerByKeyword(Pageable pageable, @RequestParam(value = "search", required = false) String keyword, Filter answerFilter) {

        Page<Answer> entities = service.getAnswerByKeyword(pageable, keyword, answerFilter);

        // convert entities --> dtos
        List<AnswerResponse> dtos = modelMapper.map(
                entities.getContent(),
                new TypeToken<List<AnswerResponse>>() {
                }.getType());

        Page<AnswerResponse> dtoPages = new PageImpl<>(dtos, pageable, entities.getTotalElements());
        return dtoPages;

    }
    @GetMapping(value = "/{id}")
    public AnswerResponse getAnswerByID(@PathVariable(name = "id") int id) {
        Answer entity = service.getQuestionByID(id);
        // convert entity to dto
        AnswerResponse answerResponse = modelMapper.map(entity, AnswerResponse.class);
        return answerResponse;
    }

    @PostMapping(value = "/create")
    public ResponseEntity<?> createAnswer(@RequestBody @Valid AnswerRequest form) {
        service.create(form);
        return new ResponseEntity<>("Created successfully", HttpStatus.OK);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<?> updateAnswerById(@PathVariable(name = "id") int id, @RequestBody @Valid AnswerRequest form){
        service.update(id,form);
        return new ResponseEntity<>("Updated successfully", HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") int id) {
        service.deleteById(id);
        return new ResponseEntity<>("Deleted successfully", HttpStatus.OK);
    }
}
