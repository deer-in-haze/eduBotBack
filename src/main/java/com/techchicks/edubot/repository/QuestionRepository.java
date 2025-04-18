package com.techchicks.edubot.repository;

import com.techchicks.edubot.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    List<Question> findTop15ByContinentAndDifficulty(String continent, String difficulty);
}
