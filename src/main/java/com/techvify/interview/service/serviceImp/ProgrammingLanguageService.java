package com.techvify.interview.service.serviceImp;

import com.techvify.interview.entity.ProgrammingLanguage;
import com.techvify.interview.repository.IProgrammingLanguageRepository;
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
public class ProgrammingLanguageService implements IService<ProgrammingLanguage> {
    @Autowired
    private IProgrammingLanguageRepository programmingLanguageRepository;

    @Override
    public ResponseEntity<Map<String, Object>> getAllPagination(Pageable pageable) {
        try {
            List<ProgrammingLanguage> programmingLanguages = new ArrayList<ProgrammingLanguage>();
            Page<ProgrammingLanguage> pageInts = programmingLanguageRepository.findAll(pageable);
            programmingLanguages = pageInts.getContent();
            Map<String,Object> response = new HashMap<>();
            response.put("programmingLanguages",programmingLanguages);
            response.put("currentPage", pageInts.getNumber() + 1);
            response.put("totalItems", pageInts.getTotalElements());
            response.put("totalPages", pageInts.getTotalPages());

            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @Override
    public ResponseEntity<ProgrammingLanguage> create(ProgrammingLanguage programmingLanguage) {
        try {
            if (programmingLanguage.getName() == null){
                HttpHeaders httpHeaders = new HttpHeaders();
                httpHeaders.add("Name is not null","headers");
                return new ResponseEntity<>(httpHeaders,HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(programmingLanguageRepository.save(programmingLanguage),HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @Override
    public ResponseEntity<ProgrammingLanguage> update(int id, ProgrammingLanguage programmingLanguage) {
        Optional<ProgrammingLanguage> optionalProgrammingLanguage = programmingLanguageRepository.findById(id);
        if (optionalProgrammingLanguage.isPresent()){
            ProgrammingLanguage newProgrammingLanguage = optionalProgrammingLanguage.get();
            if (programmingLanguage.getName() != null) {
                newProgrammingLanguage.setName(programmingLanguage.getName());
            }
            if (programmingLanguage.getName() == null){
                HttpHeaders httpHeaders = new HttpHeaders();
                httpHeaders.add("name is not null","FullName null");
                return new ResponseEntity<>(httpHeaders,HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(programmingLanguageRepository.save(newProgrammingLanguage),HttpStatus.OK);
        }else{
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("Programming Language does not exist","programmingLanguage null");
            return new ResponseEntity<>(httpHeaders,HttpStatus.BAD_REQUEST);
        }
    }
    @Override
    public ResponseEntity<HttpStatus> delete(int id) {
        try {
            Optional<ProgrammingLanguage> optionalProgrammingLanguage = programmingLanguageRepository.findById(id);
            if (!optionalProgrammingLanguage.isPresent()){
                HttpHeaders httpHeaders = new HttpHeaders();
                httpHeaders.add("Id is not exist", "ID NULL");
                return new ResponseEntity<>(httpHeaders,HttpStatus.BAD_REQUEST);
            }else {
                programmingLanguageRepository.deleteById(id);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ProgrammingLanguage find(String key) {
        return null;
    }

}
