package com.techvify.interview.repository;


import com.techvify.interview.entity.Level;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface ILevelRepository extends JpaRepository<Level, Integer>,JpaSpecificationExecutor<Level>{
	boolean existsByCode(String code);

}
