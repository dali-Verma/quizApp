package com.quiz.config;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.quiz.dao.UserRegistration;
import com.quiz.entities.Registration;



public class UserDetailServiceImple implements UserDetailsService {
	@Autowired
	 private UserRegistration userRegistration;

	@Override  
	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		//fetching user details from database
		Registration user=userRegistration.getUserByUserName(username);
		if(user==null)
		{
			throw new UsernameNotFoundException("could not found user!!");
		}
		CustomUserDetails customUserDetails=new CustomUserDetails(user);
		return customUserDetails;
	}

}
