package com.techchicks.edubot.repository;

import com.techchicks.edubot.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    List<Question> findByContinentAndDifficulty(String continent, String difficulty);

    List<Question> findByDifficulty(String difficulty);
}
