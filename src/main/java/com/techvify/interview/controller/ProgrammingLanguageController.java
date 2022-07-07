package com.techvify.interview.controller;

import com.techvify.interview.entity.ProgrammingLanguage;
import com.techvify.interview.service.serviceImp.ProgrammingLanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/programming-language")
public class ProgrammingLanguageController {
    @Autowired
    private ProgrammingLanguageService programmingLanguageService;

    @GetMapping
    public ResponseEntity<Map<String,Object>> getAllPagination(Pageable pageable){
        return programmingLanguageService.getAllPagination(pageable);
    }
    @PostMapping
    public ResponseEntity<ProgrammingLanguage> create(@RequestBody ProgrammingLanguage programmingLanguage){
        return programmingLanguageService.create(programmingLanguage);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProgrammingLanguage> update(@PathVariable("id") int id, @RequestBody ProgrammingLanguage programmingLanguage){
        return programmingLanguageService.update(id,programmingLanguage);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") int id){
        return programmingLanguageService.delete(id);
    }
}
