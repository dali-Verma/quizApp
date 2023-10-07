package com.quiz.entities;

import jakarta.persistence.Column;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Question {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(name="questionNo")
	private String questionNo;
	private String option1;
	private String option2;
	private String option3;
	private String option4;
	private String rightAnswer;
	private String category;
	public String getChose() {
		return chose;
	}
	public void setChose(String chose) {
		this.chose = chose;
	}
	private String chose;
	@ManyToOne
	private Registration user;
	public String getRightAnswer() {
		return rightAnswer;
	}
	public void setRightAnswer(String rightAnswer) {
		this.rightAnswer = rightAnswer;
	}
	public Registration getUser() {
		return user;
	}
	public void setUser(Registration user) {
		this.user = user;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getQuestionNo() {
		return questionNo;
	}
	public void setQuestionNo(String questionNo) {
		this.questionNo = questionNo;
	}
	public String getOption1() {
		return option1;
	}
	public void setOption1(String option1) {
		this.option1 = option1;
	}
	public String getOption2() {
		return option2;
	}
	public void setOption2(String option2) {
		this.option2 = option2;
	}
	public String getOption3() {
		return option3;
	}
	public void setOption3(String option3) {
		this.option3 = option3;
	}
	public String getOption4() {
		return option4;
	}
	public void setOption4(String option4) {
		this.option4 = option4;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	@Override
	public String toString() {
		return "Question [id=" + id + ", questionNo=" + questionNo + ", option1=" + option1 + ", option2=" + option2
				+ ", option3=" + option3 + ", option4=" + option4 + ", rightAnswer=" + rightAnswer + ", category=" + category + ",chose="+chose+"]";
	}
	public Question(int id, String questionNo, String option1, String option2, String option3, String option4,
			String rightAnswer, String category,String chose) {
		super();
		this.id = id;
		this.questionNo = questionNo;
		this.option1 = option1;
		this.option2 = option2;
		this.option3 = option3;
		this.option4 = option4;
		this.rightAnswer = rightAnswer;
		this.category = category;
		this.chose=chose;
	}
	public Question() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
