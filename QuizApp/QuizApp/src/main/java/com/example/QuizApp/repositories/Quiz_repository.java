package com.example.QuizApp.repositories;

import com.example.QuizApp.models.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Quiz_repository extends JpaRepository<Quiz, Integer> {

    @Query("select q from Quiz q where q.general_quiz_id = ?1")
    List<Quiz> take_by_id(Integer general_quiz_id);

    @Query("select q from Quiz q where q.id = ?1 and q.general_quiz_id = ?2")
    Quiz findByGeneral_quiz_idAndId(Integer id, Integer general);
}
