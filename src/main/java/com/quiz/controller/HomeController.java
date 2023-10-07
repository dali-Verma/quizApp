package com.quiz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import com.quiz.dao.UserRegistration;
import com.quiz.entities.Registration;

@Controller
public class HomeController {
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	UserRegistration userRegistration;
	@RequestMapping("/")
	
	public String homePage(Model model)
	{
		model.addAttribute("title","User home page");
		return "signin.html";
	}
	@RequestMapping("/signup")
	public String loginPage(Model model)
	{
		model.addAttribute("title","User registration page");
		return "signup.html";
	}
	
	@GetMapping("/signin")
	public String customLogin(Model model)
	{
		model.addAttribute("title","user login page");
		model.addAttribute("user",new Registration());

		return "signin";
	}
	//for handle the registration page
	@PostMapping("/registration")
	public String userRegistration(@ModelAttribute("user") Registration user,Model model)
	{
		//System.out.println("user name is:"+	user.getUserName());
		try {
		user.setPassword(passwordEncoder.encode(user.getPassword()));

		user.setRole("ROLE_USER");
		userRegistration.save(user);
		model.addAttribute("user",new Registration());
		return "signup";
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return "signup";
	}

}
