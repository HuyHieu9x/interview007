package com.techvify.interview.service.interfaceservice;

import com.techvify.interview.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;


public interface IUserService extends UserDetailsService{
	
	List<User> getAllUsers();

}
