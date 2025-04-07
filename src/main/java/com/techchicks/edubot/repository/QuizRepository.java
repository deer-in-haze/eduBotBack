package com.techchicks.edubot.repository;

import com.techchicks.edubot.model.Quiz;
import com.techchicks.edubot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizRepository extends JpaRepository<Quiz, Long> {
}
