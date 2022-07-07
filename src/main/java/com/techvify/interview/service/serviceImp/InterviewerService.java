package com.techvify.interview.service.serviceImp;

import com.techvify.interview.entity.Interviewer;
import com.techvify.interview.repository.IInterviewerRepository;
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
public class InterviewerService implements IService<Interviewer> {
    @Autowired
    private IInterviewerRepository interviewerRepository;

    @Override
    public ResponseEntity<Map<String, Object>> getAllPagination
            (Pageable pageable) {
        try {
            List<Interviewer> interviewers = new ArrayList<Interviewer>();
            Page<Interviewer> pageInts = interviewerRepository.findAll(pageable);

            interviewers = pageInts.getContent();

            Map<String, Object> response = new HashMap<>();

            response.put("interviewers", interviewers);
            response.put("currentPage", pageInts.getNumber() + 1);
            response.put("totalItems", pageInts.getTotalElements());
            response.put("totalPages", pageInts.getTotalPages());

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Interviewer> create(Interviewer interviewer) {
        try {
            if (interviewer.getFullName() == null) {
                HttpHeaders httpHeaders = new HttpHeaders();
                httpHeaders.add("FullName is not null", "headers");
                return new ResponseEntity<>(httpHeaders, HttpStatus.BAD_REQUEST);
            }

            return new ResponseEntity<>(interviewerRepository.save(interviewer), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @Override
    public ResponseEntity<Interviewer> update(int id, Interviewer interviewer) {
        Optional<Interviewer> interviewerOptional = interviewerRepository.findById(id);

        if (interviewerOptional.isPresent()) {
            Interviewer interviewerNew = interviewerOptional.get();

            interviewerNew.setFullName(interviewer.getFullName());
            interviewerNew.setTitleId(interviewer.getTitleId());

            return new ResponseEntity<>(interviewerRepository.save(interviewerNew), HttpStatus.OK);
        } else {
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("Interview is not exist", "Interviewer null");
            return new ResponseEntity<>(httpHeaders, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<HttpStatus> delete(int id) {
        try {
            Optional<Interviewer> interviewerOptional = interviewerRepository.findById(id);

            if (!interviewerOptional.isPresent()) {
                HttpHeaders httpHeaders = new HttpHeaders();
                httpHeaders.add("Id is not exist", "ID NULL");
                return new ResponseEntity<>(httpHeaders, HttpStatus.BAD_REQUEST);
            } else {
                interviewerRepository.deleteById(id);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public Interviewer find(String fullName) {
        return interviewerRepository.findByFullName(fullName);
    }


}
