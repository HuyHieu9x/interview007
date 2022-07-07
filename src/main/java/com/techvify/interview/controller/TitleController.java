package com.techvify.interview.controller;

import com.techvify.interview.config.responseMessage.ResponseMessage;
import com.techvify.interview.entity.Title;
import com.techvify.interview.payLoad.request.TitleRequest;
import com.techvify.interview.service.serviceImp.TitleService;
import com.techvify.interview.validation.title.TitleIdExists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/title")
@Validated
public class TitleController {
    @Autowired
    private TitleService titleService;

    @GetMapping
    public ResponseEntity<List<Title>> getAll(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "4") int size
            ,@RequestParam(value = "isDeleted", required = false, defaultValue = "false") boolean isDeleted) {
        return titleService.getAllPagination(page, size, isDeleted);
    }

    @PostMapping
    public ResponseEntity<Title> create(@RequestBody @Valid TitleRequest titleRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            return titleService.create(titleRequest);
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<Title> update(@TitleIdExists @PathVariable("id") int id, @RequestBody @Valid TitleRequest titleRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            return titleService.update(id,titleRequest);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@TitleIdExists @PathVariable("id") int id) {
        return titleService.delete(id);
    }

    @GetMapping("/{name}")
    public Title getByName(@PathVariable("name") String name) {
        return titleService.find(name);
    }
}
