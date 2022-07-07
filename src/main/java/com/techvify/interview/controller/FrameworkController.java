package com.techvify.interview.controller;

import com.techvify.interview.entity.Framework;
import com.techvify.interview.service.serviceImp.FrameworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/framework")
public class FrameworkController {
    
    @Autowired
    private FrameworkService frameworkService;
    
    @GetMapping
    public ResponseEntity<Map<String,Object>> getAllPagination(Pageable pageable){
        return frameworkService.getAllPagination(pageable);
    }
    @PostMapping
    public ResponseEntity<Framework> create(@RequestBody Framework framework){
        return frameworkService.create(framework);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Framework> update(@PathVariable("id") int id, @RequestBody Framework framework){
        return frameworkService.update(id,framework);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") int id){
        return frameworkService.delete(id);
    }
}