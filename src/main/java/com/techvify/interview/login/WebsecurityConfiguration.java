package com.techvify.interview.login;

import com.techvify.interview.service.interfaceservice.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;


@Component
@EnableWebSecurity
public class WebsecurityConfiguration extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private IUserService service;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(service).passwordEncoder(new BCryptPasswordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
			.antMatchers("/api/v1/login").anonymous()
			.anyRequest().permitAll()
			.and()
			.httpBasic()
			.and()
			.csrf().disable()
			.addFilterBefore(
					new JWTAuthenticationFilter("/api/v1/login", authenticationManager()), 
					UsernamePasswordAuthenticationFilter.class) 
			.addFilterBefore(
					new JWTAuthorizationFilter(), 
					UsernamePasswordAuthenticationFilter.class);
	}
	
}
