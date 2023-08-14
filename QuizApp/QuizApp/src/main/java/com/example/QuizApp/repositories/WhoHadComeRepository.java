package com.example.QuizApp.repositories;

import com.example.QuizApp.models.WhoHadCome;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface WhoHadComeRepository extends JpaRepository<WhoHadCome, Integer> {
}
