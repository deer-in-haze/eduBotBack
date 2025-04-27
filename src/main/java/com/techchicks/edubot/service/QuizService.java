package com.techchicks.edubot.service;

import com.techchicks.edubot.model.Quiz;
import com.techchicks.edubot.repository.QuizRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class QuizService {

    private final QuizRepository quizRepository;

    public List<Quiz> getAllQuizzes() {
        return quizRepository.findAll();
    }

    public Optional<Quiz> getQuizById(Long id) {
        return quizRepository.findById(id);
    }

    public void createQuiz(Quiz quiz) {
        quizRepository.save(quiz);
    }

    public void deleteQuiz(Long id) {
        Optional<Quiz> existingQuizOpt = quizRepository.findById(id);
        if (existingQuizOpt.isPresent()) {
            Quiz existingQuiz = existingQuizOpt.get();
            quizRepository.delete(existingQuiz);
        } else {
            throw new RuntimeException("Quiz not found with id " + id);
        }
    }
}
