package com.techvify.interview.service.serviceImp;

import com.techvify.interview.entity.InterviewSession;
import com.techvify.interview.repository.IInterviewSessionRepository;
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
public class InterviewSessionService implements IService<InterviewSession> {

    @Autowired
    private IInterviewSessionRepository interviewSessionRepository;

    @Override
    public ResponseEntity<Map<String, Object>> getAllPagination(Pageable pageable) {
        try {
            List<InterviewSession> interviewSessions = new ArrayList<InterviewSession>();
//            Pageable pageable = PageRequest.of(page - 1,size);

            Page<InterviewSession> interviewSessionPage = interviewSessionRepository.findAll(pageable);
            interviewSessions = interviewSessionPage.getContent();

            Map<String,Object> response = new HashMap<>();

            response.put("interviewerSessions",interviewSessions);
            response.put("currentPage", interviewSessionPage.getNumber() + 1);
            response.put("totalItems", interviewSessionPage.getTotalElements());
            response.put("totalPages", interviewSessionPage.getTotalPages());

            return new ResponseEntity<>(response,HttpStatus.OK);

        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @Override
    public ResponseEntity<InterviewSession> create(InterviewSession interviewSession) {
        try {
            if (interviewSession.getInterviewee() == null){
                HttpHeaders httpHeaders = new HttpHeaders();
                httpHeaders.add("Interviewee is not null","Interviewee NULL");
                return new ResponseEntity<>(httpHeaders,HttpStatus.BAD_REQUEST);
            }

            if (interviewSession.getInterviewerSet() == null){
                HttpHeaders httpHeaders = new HttpHeaders();
                httpHeaders.add("Interviewer is not null","Interviewer NULL");
                return new ResponseEntity<>(httpHeaders,HttpStatus.BAD_REQUEST);
            }

            if (interviewSession.getQuestionSet() == null){
                HttpHeaders httpHeaders = new HttpHeaders();
                httpHeaders.add("Question is not null","Question NULL");
                return new ResponseEntity<>(httpHeaders,HttpStatus.BAD_REQUEST);
            }

            return new ResponseEntity<>(interviewSessionRepository.save(interviewSession),HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<InterviewSession> update(int id, InterviewSession interviewSession) {
        Optional<InterviewSession> interviewSessionOptional = interviewSessionRepository.findById(id);

        if (interviewSessionOptional.isPresent()){
            InterviewSession interviewSessionNew = interviewSessionOptional.get();

            if (interviewSession.getInterviewee() != null) {
                interviewSessionNew.setInterviewee(interviewSession.getInterviewee());
            }

            if (interviewSession.getInterviewee() == null){
                HttpHeaders httpHeaders = new HttpHeaders();
                httpHeaders.add("Interviewee is not null","Interviewee NULL");
                return new ResponseEntity<>(httpHeaders,HttpStatus.BAD_REQUEST);
            }

            if (interviewSession.getInterviewerSet() != null){
                interviewSessionNew.setInterviewerSet(interviewSession.getInterviewerSet());
            }

            if (interviewSession.getInterviewerSet() == null){
                HttpHeaders httpHeaders = new HttpHeaders();
                httpHeaders.add("Interviewer is not null","Interviewer null");
                return new ResponseEntity<>(httpHeaders,HttpStatus.BAD_REQUEST);
            }

            if (interviewSession.getQuestionSet() != null){
                interviewSessionNew.setQuestionSet(interviewSession.getQuestionSet());
            }

            if (interviewSession.getQuestionSet() == null){
                HttpHeaders httpHeaders = new HttpHeaders();
                httpHeaders.add("Question is not null","Question null");
                return new ResponseEntity<>(httpHeaders,HttpStatus.BAD_REQUEST);
            }

            return new ResponseEntity<>(interviewSessionRepository.save(interviewSessionNew),HttpStatus.OK);

        }else{
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("InterviewSession does not exist","InterviewSession NULL");
            return new ResponseEntity<>(httpHeaders,HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<HttpStatus> delete(int id) {
        interviewSessionRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public InterviewSession find(String key) {
        return interviewSessionRepository.findByIntervieweeName(key);
    }
}
