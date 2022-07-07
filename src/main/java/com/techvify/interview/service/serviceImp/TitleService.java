package com.techvify.interview.service.serviceImp;

import com.techvify.interview.config.responseMessage.ResponseMessage;
import com.techvify.interview.entity.Title;
import com.techvify.interview.payLoad.request.TitleRequest;
import com.techvify.interview.repository.ITitleRepository;
import com.techvify.interview.service.interfaceservice.ITitleService;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.*;

@Service
public class TitleService implements ITitleService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ITitleRepository titleRepository;

    @Autowired
    private EntityManager entityManager;

    @Override
    public ResponseEntity<List<Title>> getAllPagination(int page, int size,boolean isDeleted) {
        Session session = entityManager.unwrap(Session.class);
        Filter filter = session.enableFilter("deletedTitleFilter");
        filter.setParameter("isDeleted", isDeleted);

        List<Title> titleList = titleRepository.findAllPaginate((page - 1) * size, size,isDeleted);

        session.disableFilter("deletedTitleFilter");

        return new ResponseEntity<>(titleList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Title> create(TitleRequest titleRequest) {
        try {
            Title title = modelMapper.map(titleRequest, Title.class);
            return new ResponseEntity<>(titleRepository.save(title), HttpStatus.CREATED);
        } catch (Exception e) {
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add(e.getMessage(), "CREATE FAIL");
            return new ResponseEntity<>(httpHeaders, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<Title> update(int id, TitleRequest titleRequest) {
        try {
            Title title = titleRepository.findById(id).get();
            title.setName(titleRequest.getName());

            return new ResponseEntity<>(titleRepository.save(title), HttpStatus.OK);
        } catch (Exception e) {
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add(e.getMessage(), "UPDATE FAIL");
            return new ResponseEntity<>(httpHeaders, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<?> delete(int id) {
        try {
            Title title = titleRepository.findById(id).get();

            if (title.getInterviewerList().size() == 0){
                titleRepository.deleteById(id);
                ResponseMessage responseMessage = new ResponseMessage(HttpStatus.OK,"Delete successfully");
                return ResponseEntity.ok().body(responseMessage);
            }

            ResponseMessage responseMessage2 = new ResponseMessage(HttpStatus.BAD_REQUEST,"Exists interviewer have this title");
            return ResponseEntity.badRequest().body(responseMessage2);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public Title find(String key) {
        return titleRepository.findByName(key);
    }

    @Override
    public boolean titleNameExists(String name) {
        return titleRepository.existsByName(name);
    }

    @Override
    public boolean titleIdExists(int id) {
        return titleRepository.existsById(id);
    }
}
