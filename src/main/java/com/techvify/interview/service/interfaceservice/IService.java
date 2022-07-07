package com.techvify.interview.service.interfaceservice;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.Map;

public interface IService<E> {
        ResponseEntity<Map<String, Object>> getAllPagination(Pageable pageable);
        ResponseEntity<E> create(E e);
        ResponseEntity<E> update(int id , E e);
        ResponseEntity<HttpStatus> delete(int id);
        E find(String key);
}
