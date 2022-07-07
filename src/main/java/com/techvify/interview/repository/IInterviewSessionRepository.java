package com.techvify.interview.repository;

import com.techvify.interview.entity.InterviewSession;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IInterviewSessionRepository extends JpaRepository<InterviewSession,Integer> {
    Page<InterviewSession> findAll(Pageable pageable);
    InterviewSession findByIntervieweeName(String name);
}
