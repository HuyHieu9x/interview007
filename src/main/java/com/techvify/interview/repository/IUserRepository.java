package com.techvify.interview.repository;

import com.techvify.interview.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface IUserRepository extends JpaRepository<User, Integer>{
	
	public User findByUserName(String name);
}
