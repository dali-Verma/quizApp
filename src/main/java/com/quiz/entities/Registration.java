package com.quiz.entities;




import java.util.ArrayList;

import java.util.List;



import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.UniqueConstraint;


@Entity
public class Registration {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String userName;
	@Column(unique = true)
	private String email;
	private String password;
	private String role;
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY,mappedBy = "user")
	private List<Question> questions=new ArrayList<>();
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public List<Question> getQuestions() {
		return questions;
	}
	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}
	@Override
	public String toString() {
		return "Registration [id=" + id + ", userName=" + userName + ", email=" + email + ", password=" + password
				+ ", role=" + role + ",questions="+questions+"]";
	}
	public Registration() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Registration(int id, String userName, String email, String password, String role) {
		super();
		this.id = id;
		this.userName = userName;
		this.email = email;
		this.password = password;
		this.role = role;
	}

}
