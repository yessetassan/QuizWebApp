package com.example.QuizApp.repositories;

import com.example.QuizApp.models.Saved_Answers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface Saved_AnswersRepository extends JpaRepository<Saved_Answers,Integer> {

    @Query("select s from Saved_Answers  s where s.person_id = ?1")
    List<Saved_Answers> findAllByPerson_id(Integer id);

    @Query("select  distinct s.posted_date from Saved_Answers s where s.person_id = ?1")
    List<Timestamp> findByTimestamp(Integer person_id);

}
