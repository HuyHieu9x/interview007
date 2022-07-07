package com.techvify.interview.controller;

import com.techvify.interview.entity.Interviewer;
import com.techvify.interview.service.serviceImp.InterviewerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/interviewer")
public class InterviewerController {
    @Autowired
    private InterviewerService interviewerService;
    @GetMapping
    public ResponseEntity<Map<String,Object>> getAllPagination(Pageable pageable){
        return interviewerService.getAllPagination(pageable);
    }
    @PostMapping
    public ResponseEntity<Interviewer> create(@RequestBody Interviewer interviewer){
        return interviewerService.create(interviewer);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Interviewer> update(@PathVariable("id") int id, @RequestBody Interviewer interviewer){
        return interviewerService.update(id,interviewer);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") int id){
        return interviewerService.delete(id);
    }

    @GetMapping("/{name}")
    public Interviewer getByFullName(@PathVariable("name") String name){
        return interviewerService.find(name);
    }

}
