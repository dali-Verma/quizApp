package com.quiz.entities;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class QuestionWrapper {
	private List<Question> question;

	public QuestionWrapper() {
		super();
		// TODO Auto-generated constructor stub
	}

	public QuestionWrapper(List<Question> question) {
		super();
		this.question = question;
	}

	@Override
	public String toString() {
		return "QuestionWrapper [question=" + question + "]";
	}

	public List<Question> getQuestion() {
		return question;
	}

	public void setQuestion(List<Question> question) {
		this.question = question;
	}

}
