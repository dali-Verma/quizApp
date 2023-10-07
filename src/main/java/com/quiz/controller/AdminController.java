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
import org.springframework.web.bind.annotation.RequestMapping;

import com.quiz.dao.QuestionService;
import com.quiz.dao.UserRegistration;
import com.quiz.entities.Question;
import com.quiz.entities.QuestionWrapper;
import com.quiz.entities.Registration;
import com.quiz.helper.Message;
import com.quiz.service.AdminService;
import com.quiz.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	public UserService userService;
	@Autowired
	public AdminService adminService;
	@Autowired
	public QuestionService questionService;
	@Autowired
	public UserRegistration userRegistration;
	@Autowired
	public PasswordEncoder passwordEncoder;
	@RequestMapping("/index")
	public String adminDashboard(Model model,Principal principal)
	{
		String adminName=principal.getName();
		Registration admin= userRegistration.getUserByUserName(adminName);
		model.addAttribute("admin", admin);
		return "admin/adminHome.html";
	}
	//open add question page
	@GetMapping("/add-question")
	public String openQuestionForm(Model model,Principal principal)
	{
		String adminName=principal.getName();
		Registration admin= userRegistration.getUserByUserName(adminName);
		model.addAttribute("admin", admin);

		model.addAttribute("title","add question page");
		model.addAttribute("question", new Question());
		return "admin/addQuestion.html";
	}
	//add question to the database
	@PostMapping("/process-question")
	public String addQuestion(@ModelAttribute Question question,Principal principal,Model model,HttpSession session)
	{
		try {
			String adminName=principal.getName();
			Registration admin= userRegistration.getUserByUserName(adminName);
			model.addAttribute("admin", admin);

			model.addAttribute("title","add question page");
			model.addAttribute("question", new Question());
			question.setUser(admin);
			questionService.save(question);
			session.setAttribute("message", new Message("Question is added to the database!", "success"));
			//System.out.println(question);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		session.setAttribute("message", new Message("Somthing went wrong!", "danger"));

		}
		return "admin/addQuestion.html";
	}
	//for display the question
	@GetMapping("/show-question")
	public String showQuestion(Principal principal,Model model)
	{
		String adminName=principal.getName();
		Registration admin= userRegistration.getUserByUserName(adminName);
		model.addAttribute("admin", admin);

		model.addAttribute("title","show questions");
		
		
		
		model.addAttribute("questions", adminService.getAllQuestion());
		return "admin/showQuestion.html";
	}
	//for display all users
	@GetMapping("/show-user")
	public String showUser(Principal principal,Model model)
	{
		String adminName=principal.getName();
		Registration admin= userRegistration.getUserByUserName(adminName);
		model.addAttribute("admin", admin);
		List<Registration> user=userRegistration.findAll();
		model.addAttribute("user", user);

		return "admin/showUser.html";
	}
	//for delete user
	@GetMapping("/delete/{id}")
	public String deleteUser(@PathVariable("id")  Integer id,HttpSession session)
	{
		Optional<Registration> user=this.userRegistration.findById(id);
		//System.out.println(user);
		this.userRegistration.deleteById(id);
		session.setAttribute("message", new Message("Question is deleted!", "success"));

		return "redirect:/admin/show-user";
	}
	//for delete question
	@GetMapping("deleteQ/{id}")
	public String deleteQuestion(@PathVariable("id") Integer id)
	{
		Question q=this.questionService.findById(id).get();
		questionService.delete(q);
		return "redirect:/admin/show-question";
	}
	//for update question
	@PostMapping("/update-question/{id}")
	public String openUpdateForm(@PathVariable("id") Integer id,Model m,Principal principal)
	{
		String adminName=principal.getName();
		Registration admin= userRegistration.getUserByUserName(adminName);
		m.addAttribute("admin", admin);
		m.addAttribute("title", "Update Question form");
		m.addAttribute("question", questionService.findById(id).get());
		return "admin/updateQ.html";
	}
	//for question update
	@PostMapping("/process-update")
	public String updateQuestion(@ModelAttribute Question question,Principal principal,Model model,HttpSession session)
	{
		try {
			String adminName=principal.getName();
			Registration admin= userRegistration.getUserByUserName(adminName);
			model.addAttribute("admin", admin);

			model.addAttribute("title","update question");
			Question q1=questionService.findById(question.getId()).get();
			question.setUser(admin);
			//System.out.println("q"+q1);
			
			
			questionService.save(question);
			session.setAttribute("message", new Message("Question is updated to the database!", "success"));
			//System.out.println(question);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		session.setAttribute("message", new Message("Somthing went wrong!", "danger"));

		}
		return "redirect:/admin/show-question";
	}

}
