package com.techvify.interview.controller;

import com.techvify.interview.entity.InterviewSession;
import com.techvify.interview.service.serviceImp.InterviewSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
@RestController
@RequestMapping("/api/interview-session")
public class InterviewSessionController {
    @Autowired
    private InterviewSessionService interviewSessionService;
    @GetMapping
    public ResponseEntity<Map<String,Object>> getAllPagination(Pageable pageable){
        return interviewSessionService.getAllPagination(pageable);
    }
    @PostMapping
    public ResponseEntity<InterviewSession> create(@RequestBody InterviewSession interviewSession){
        return interviewSessionService.create(interviewSession);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InterviewSession> update(@PathVariable("id") int id, @RequestBody InterviewSession interviewSession){
        return interviewSessionService.update(id,interviewSession);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") int id){
        return interviewSessionService.delete(id);
    }

    @GetMapping("/{name}")
    public InterviewSession getByFullname(@PathVariable("name") String name){
        return interviewSessionService.find(name);
    }

}