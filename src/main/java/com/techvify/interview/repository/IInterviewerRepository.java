package com.techvify.interview.repository;

import com.techvify.interview.entity.Interviewer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IInterviewerRepository extends JpaRepository<Interviewer,Integer> {
    Page<Interviewer> findAll(Pageable pageable);
    Interviewer findByFullName(String fullName);
}
