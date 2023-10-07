package com.quiz.controller;

import java.security.Principal;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.quiz.dao.QuestionService;
import com.quiz.dao.UserRegistration;
import com.quiz.entities.Question;
import com.quiz.entities.QuestionWrapper;
import com.quiz.entities.Registration;

import com.quiz.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	
	@Autowired
	public UserService userService;
	@Autowired
	public QuestionService questionService;
	@Autowired
	public UserRegistration userRegistration;
	@Autowired
	public PasswordEncoder passwordEncoder;
	

	
	
	
	@RequestMapping("/index")
	public String userDashboard(Model model,Principal principal)
	{
		String userName=principal.getName();
		Registration user=this.userRegistration.getUserByUserName(userName);
		model.addAttribute("user", user);
		model.addAttribute("title","User Dashboard");
		return "user/userDashboard.html";
	}
	//for open quiz page
	@GetMapping("/quiz-page/{category}")
	public String openQuizPage(Model model,Principal principal,@PathVariable("category") String category)
	{
		String userName=principal.getName();
		Registration user=this.userRegistration.getUserByUserName(userName);
		model.addAttribute("user", user);
		model.addAttribute("title","Quiz Page");
		QuestionWrapper qRapo=userService.getQuestion(category);
		model.addAttribute("qRapo",qRapo);
		//System.out.println("cat:"+category);
		model.addAttribute("cat", category);
		return "user/quizPage.html";
	}
	//for calculate the result
	@PostMapping("/quiz-result/{category}")
	public String result(@ModelAttribute QuestionWrapper qRapo,Model model,Principal principal,@PathVariable("category") String category)
	{
		String userName=principal.getName();
		Registration user=this.userRegistration.getUserByUserName(userName);
		model.addAttribute("user", user);
		model.addAttribute("title","Result Page");
		try {
		QuestionWrapper qRapo1=userService.getQuestion(category);

		//model.addAttribute("results",userService.calculateResult(result,category));
		//System.out.println(qRapo);
		int k=0;
		int count=0;
		int total=0;
		for(Question q:qRapo.getQuestion())
		{
			total++;
			if(qRapo1.getQuestion().get(k).getRightAnswer().equals(q.getChose()))
			
			count++;
			k++;
			
			
		}
		
		
		//System.out.println("Total correct answer:"+count);
		
		model.addAttribute("ans",count);
		model.addAttribute("wrong",(total-count));
		model.addAttribute("count",total);
		model.addAttribute("qRapo", qRapo);

		//for checking
		
		return "user/result.html"; 
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return "user/result.html"; 

	}
	//for deleting the user
	
}
