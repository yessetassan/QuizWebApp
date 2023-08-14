package com.example.QuizApp.repositories;

import com.example.QuizApp.models.General_Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface General_quizRepository extends JpaRepository<General_Quiz, Integer> {

}
