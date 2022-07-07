package com.techvify.interview.service.interfaceservice;

import com.techvify.interview.config.responseMessage.ResponseMessage;
import com.techvify.interview.entity.Title;
import com.techvify.interview.payLoad.request.TitleRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ITitleService {
    ResponseEntity<List<Title>> getAllPagination(int page, int size,boolean isDeleted);
    ResponseEntity<Title> create(TitleRequest titleRequest);
    ResponseEntity<Title> update(int id, TitleRequest titleRequest);
    ResponseEntity<?> delete(int id);
    Title find(String key);
    boolean titleNameExists(String name);
    boolean titleIdExists(int id);
}
