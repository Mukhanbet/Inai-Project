package com.example.InaiProject.repository;

import com.example.InaiProject.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    @Query(value = "SELECT * FROM questions as q ORDER BY RANDOM() LIMIT 10", nativeQuery = true)
    List<Question> findRandomQuestions();

}
