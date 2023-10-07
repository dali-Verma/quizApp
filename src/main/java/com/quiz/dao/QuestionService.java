package com.quiz.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.quiz.entities.Question;
import com.quiz.entities.QuestionWrapper;


@Repository
public interface QuestionService extends JpaRepository<Question, Integer> {
	 public List<Question> findAll();
	 @Query("select u from Question u where u.category=:category")
	public List<Question> getByCategory(@Param("category") String category);
	
}
