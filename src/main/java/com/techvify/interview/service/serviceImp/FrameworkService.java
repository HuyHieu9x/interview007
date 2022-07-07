package com.techvify.interview.service.serviceImp;

import com.techvify.interview.entity.Framework;
import com.techvify.interview.repository.IFrameworkRepository;

import com.techvify.interview.service.interfaceservice.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FrameworkService implements IService<Framework> {
    @Autowired
    private IFrameworkRepository frameworkRepository;
    @Override
    public ResponseEntity<Map<String, Object>> getAllPagination(Pageable pageable) {
        try {
            List<Framework> frameworks = new ArrayList<Framework>();
//            Pageable pageable = PageRequest.of(page - 1,size);
            Page<Framework> pageInts = frameworkRepository.findAll(pageable);
            frameworks = pageInts.getContent();
            Map<String,Object> response = new HashMap<>();
            response.put("Frameworks",frameworks);
            response.put("currentPage", pageInts.getNumber() + 1);
            response.put("totalItems", pageInts.getTotalElements());
            response.put("totalPages", pageInts.getTotalPages());
            return new ResponseEntity<>(response,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Framework> create(Framework framework) {
        try {
            if (framework.getName() == null){
                HttpHeaders httpHeaders = new HttpHeaders();
                httpHeaders.add("Name is not null","headers");
                return new ResponseEntity<>(httpHeaders,HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(frameworkRepository.save(framework),HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Framework> update(int id, Framework framework) {
        Optional<Framework> optionalFramework = frameworkRepository.findById(id);
        if (optionalFramework.isPresent()){
            Framework newFramework = optionalFramework.get();
            if (framework.getName() != null) {
                newFramework.setName(framework.getName());
            }

            if (framework.getName() == null){
                HttpHeaders httpHeaders = new HttpHeaders();
                httpHeaders.add("name is not null","Name null");
                return new ResponseEntity<>(httpHeaders,HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(frameworkRepository.save(newFramework),HttpStatus.OK);
        }else{
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("Framework is not exist","Framework null");
            return new ResponseEntity<>(httpHeaders,HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<HttpStatus> delete(int id) {
        try {
            Optional<Framework> optionalFramework = frameworkRepository.findById(id);
            if (!optionalFramework.isPresent()){
                HttpHeaders httpHeaders = new HttpHeaders();
                httpHeaders.add("Id is not exist", "ID NULL");
                return new ResponseEntity<>(httpHeaders,HttpStatus.BAD_REQUEST);
            }else {
                frameworkRepository.deleteById(id);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public Framework find(String key) {
        return null;
    }

}
