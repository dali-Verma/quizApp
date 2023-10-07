package com.quiz.service;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quiz.dao.QuestionService;
import com.quiz.entities.Question;
import com.quiz.entities.QuestionWrapper;


@Service
public class UserService {
	
	@Autowired
	public QuestionWrapper qRapo;
	@Autowired
	public QuestionService questionService;

	public QuestionWrapper getQuestion(String category) {
		List<Question> question=questionService.getByCategory(category);
		
		qRapo.setQuestion(question);
		return qRapo;
	}

	public int getResult(QuestionWrapper qRapo2, String category) {
		int correct=0;
		

		for(Question quiz:qRapo2.getQuestion())
		{
			if(quiz.getRightAnswer()==quiz.getChose())
			correct++;
		}
		return correct;
	}
	
	
	
/*
	public int getResult(QuestionWrapper qRapo,String category) {

			int correct = 0;
		
		for(Question q:qRapo.getQuestion())
		{
			//if(q.getRightAnswer()==q.getChose())
			
				correct++;
		}
		return correct;
		
	}

	

	

	public int calculateResult(Result result, String category) {
		List<Question> question=questionService.getByCategory(category);
		List<String>=result.getAnswer();
		int right=0;
		int i=0;
		for(Result results:result)
		{
			if(results.getAnswer().equals(question.get(i).getRightAnswer()))
				right++;
			
			i++;
		}
		return right;
	}
	*/

}
