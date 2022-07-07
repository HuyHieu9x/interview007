package com.techvify.interview.service.interfaceservice;

import com.techvify.interview.entity.InterviewSession;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IInterviewSession extends JpaRepository<InterviewSession,Integer> {
    Page<InterviewSession> findAll(Pageable pageable);
}
