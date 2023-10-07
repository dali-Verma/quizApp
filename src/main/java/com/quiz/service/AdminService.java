package com.quiz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quiz.dao.QuestionService;
import com.quiz.entities.Question;
@Service
public class AdminService {
@Autowired
public	QuestionService questionService;
	public List<Question> getAllQuestion()
	{
		return this.questionService.findAll();
		
	}

}
